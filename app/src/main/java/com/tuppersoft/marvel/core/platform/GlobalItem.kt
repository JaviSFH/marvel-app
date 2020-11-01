package com.tuppersoft.marvel.core.platform

abstract class GlobalItem {

    abstract val typeItem: TypeItem

    enum class TypeItem(val value: Int) {
        NORMAL(1), SHIMMER(2),
    }

}
