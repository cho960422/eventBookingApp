package com.example.domain.entities.paging

/**
 * K: 페이징 시 사용할 Key Type
 * T: 페이징 결과로 반환 받을 목록 Type
 */
data class PagingResult<K, T>(
    val entries: List<T>,
    val prevKey: K? = null,
    val nextKey: K? = null,
)
