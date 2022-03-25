package com.common

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 15:40
 **/
enum class ResultEnum(val code: String, val message: String) {
    SUCCESS("0000", "操作成功！"),
    ERROR("9999", "服務器開小差，請稍後再試！"),
}