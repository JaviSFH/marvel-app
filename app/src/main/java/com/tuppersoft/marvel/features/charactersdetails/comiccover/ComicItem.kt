package com.tuppersoft.marvel.features.charactersdetails.comiccover

import com.tuppersoft.domain.models.comic.Comic
import com.tuppersoft.marvel.core.platform.GlobalItem
import com.tuppersoft.marvel.core.platform.GlobalItem.TypeItem.NORMAL

data class ComicItem(val comic: Comic) : GlobalItem() {

    override val typeItem: TypeItem
        get() = NORMAL
}
