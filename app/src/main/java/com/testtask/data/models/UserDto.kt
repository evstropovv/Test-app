package com.testtask.data.models

data class UserDto(
    val id: Long = 0,
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val address: AddressDto = AddressDto(),
    val phone: String = "",
    val website: String = "",
    val company: CompanyDto = CompanyDto()
) {
    data class AddressDto(
        val street: String = "",
        val suite: String = "",
        val city: String = "",
        val zipcode: String = "",
        val geo: GeoDto = GeoDto()
    ) {
        data class GeoDto(
            val lat: Double = 0.0,
            val lng: Double = 0.0
        )
    }

    data class CompanyDto(
        val name: String = "",
        val catchPhrase: String = "",
        val bs: String = ""
    )
}