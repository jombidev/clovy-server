package tech.clovy.clovyapp.common.exception

class CustomException(val detail: ExceptionDetail, vararg val formats: Any?) : RuntimeException()
