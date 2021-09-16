package cz.sedy.router.model.client

import com.fasterxml.jackson.core.type.TypeReference

class CountryListReference : TypeReference<List<CountryResponse>>() {
}