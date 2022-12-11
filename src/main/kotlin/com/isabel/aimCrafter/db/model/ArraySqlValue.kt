package com.isabel.aimCrafter.db.model

import org.springframework.jdbc.support.SqlValue
import java.sql.PreparedStatement

class ArraySqlValue<T>(
    private val values: Array<T>,
    private val dbTypeName: String
) : SqlValue {
    override fun setValue(ps: PreparedStatement, paramIndex: Int) {
        val arrayValue = ps.connection.createArrayOf(dbTypeName, values)
        ps.setArray(paramIndex, arrayValue)
    }

    override fun cleanup() = Unit
}