package com.example.domain.entities.state

data class IssueError(
    val exception: EXCEPTION? = null,
    val code: Int? = null,
    val message: String? = null
) {

    fun getError(code: Int): IssueError {
        return if (code >= 500) IssueError(EXCEPTION.SERVER, code, message = message)
        else if (code >= 400) IssueError(EXCEPTION.CLIENT, code, message = message)
        else IssueError(EXCEPTION.UNKNOWN, code, message = message)
    }

}

enum class EXCEPTION {
    CLIENT, SERVER, IOEXCEPTION, UNKNOWN
}