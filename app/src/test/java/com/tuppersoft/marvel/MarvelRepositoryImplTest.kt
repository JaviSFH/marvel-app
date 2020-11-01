package com.tuppersoft.marvel

import com.tuppersoft.data.datasource.MarvelServices
import com.tuppersoft.data.repositories.MarvelRepositoryImpl
import com.tuppersoft.marvel.mocks.MockCheckInternetFalse
import com.tuppersoft.marvel.mocks.MockCheckInternetTrue
import com.tuppersoft.marvel.mocks.getMockHttpClient
import com.tuppersoft.marvel.mocks.getMockhttpClientWithBadToken
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarvelRepositoryImplTest {

    lateinit var userRepositoryWithInternet: MarvelRepositoryImpl
    lateinit var userRepositoryWithOutInternet: MarvelRepositoryImpl
    lateinit var userRepositoryWithBadToken: MarvelRepositoryImpl
    lateinit var marvelServicesBadToken: MarvelServices
    lateinit var marvelServices: MarvelServices
    private var mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start()

        marvelServices = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getMockHttpClient())
            .build()
            .create(MarvelServices::class.java)

        marvelServicesBadToken = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getMockhttpClientWithBadToken())
            .build()
            .create(MarvelServices::class.java)

        userRepositoryWithInternet = MarvelRepositoryImpl(marvelServices, MockCheckInternetTrue())
        userRepositoryWithOutInternet = MarvelRepositoryImpl(marvelServices, MockCheckInternetFalse())
        userRepositoryWithBadToken = MarvelRepositoryImpl(marvelServicesBadToken, MockCheckInternetTrue())
    }

    @Test
    fun get_characters() {
        runBlocking {
            val result = userRepositoryWithInternet.getCharacters(10, 0)
            result.collect { character ->
                assert(character.count ?: 0 > 0)
            }

        }
    }

    @Test
    fun get_comics() {

        runBlocking {
            val result = userRepositoryWithInternet.getCharacters(1, 0)
            result.collect { character ->
                assert(character.count ?: 0 > 0)
                val comicResult =
                    userRepositoryWithInternet.getComicsFromCharacter(character.results[0].id.toString())
                comicResult.collect { comics ->
                    assert(comics.count ?: 0 > 0)
                }
            }
        }
    }

    @Test
    fun no_internet_connection() {

        runBlocking {
            val result = userRepositoryWithOutInternet.getCharacters(10, 0)
            result.catch {
                assert(it is com.tuppersoft.domain.models.exceptions.Failure.NoInternetConnectionException)
            }.collect {
                assert(false)
            }
        }
    }

    @Test
    fun invalid_credential() {
        runBlocking {
            val result = userRepositoryWithBadToken.getCharacters(10, 0)
            result.catch {
                assert(it is com.tuppersoft.domain.models.exceptions.Failure.OthersException && it.message?.contains("InvalidCredentials")==true)
            }.collect {
                assert(false)
            }
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}



