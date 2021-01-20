package com.github.tomplum.activity.dto.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("Me")
data class PersonalData(
    @JacksonXmlProperty(isAttribute = true, localName = "HKCharacteristicTypeIdentifierDateOfBirth")
    var dateOfBirth: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "HKCharacteristicTypeIdentifierBiologicalSex")
    var sex: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "HKCharacteristicTypeIdentifierBloodType")
    var bloodType: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "HKCharacteristicTypeIdentifierFitzpatrickSkinType")
    var skinType: String?,
)