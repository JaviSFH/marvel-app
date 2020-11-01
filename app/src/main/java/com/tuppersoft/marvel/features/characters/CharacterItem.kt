package com.tuppersoft.marvel.features.characters

import com.tuppersoft.domain.models.character.Characters
import com.tuppersoft.marvel.core.platform.GlobalItem
import com.tuppersoft.marvel.core.platform.GlobalItem.TypeItem.NORMAL

data class CharacterItem(val character: Characters) : GlobalItem() {

    override val typeItem: TypeItem
        get() = NORMAL
}
