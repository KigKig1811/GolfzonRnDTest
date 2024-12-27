package com.xt.core_data.mapper

import com.xt.core_data.dto.BreedDto
import com.xt.core_data.dto.DimensionDto
import com.xt.core_data.dto.ImageDto
import com.xt.core_data.room.enity.ImageEntity
import com.xt.core_domain.model.BreedModel
import com.xt.core_domain.model.DimensionModel
import com.xt.core_domain.model.ImageModel

fun ImageDto.toImageModel() = ImageModel(
    id = id ?: "",
    url = url ?: "",
    width = width ?: 0,
    height = height ?: 0,
    breeds = breeds?.map { it.toBreedModel() } ?: emptyList()
)

fun DimensionDto.toDimensionModel() = DimensionModel(
    imperial = imperial ?: "",
    metric = metric ?: ""
)

fun BreedDto.toBreedModel() = BreedModel(
    weight = weight?.toDimensionModel() ?: DimensionModel(),
    height = height?.toDimensionModel() ?: DimensionModel(),
    id = id ?: 0,
    name = name ?: "",
    bredFor = bredFor ?: "",
    breGroup = breGroup ?: "",
    lifeSpan = lifeSpan ?: "",
    temperament = temperament ?: "",
    referenceImageId = referenceImageId ?: "",
)

fun ImageEntity.toImageModel() = ImageModel(
    id = id,
    url = url,
    width = width,
    height = height,
    isFavorited = isFavorited
)

fun ImageModel.toImageEntity() = ImageEntity(
    id = id,
    url = url,
    width = width,
    height = height,
    isFavorited = isFavorited
)
