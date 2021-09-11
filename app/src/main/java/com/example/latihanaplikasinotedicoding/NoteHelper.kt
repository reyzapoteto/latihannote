package com.example.latihanaplikasinotedicoding

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.latihanaplikasinotedicoding.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.example.latihanaplikasinotedicoding.DatabaseContract.NoteColumns.Companion._ID
import java.sql.SQLException

class NoteHelper(context: Context) {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {

        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: NoteHelper? = null

        fun getInstance(context: Context): NoteHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: NoteHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        database.close()
        if (database.isOpen) {
            database.close()
        }
    }

    // mengambil semua data berdasarkan urutan awal id

    fun getAllQuery(): Cursor {

        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC "
        )
    }

    // mengambil query berdasarkan Id
    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    // menginsert data
    fun insert(values: ContentValues): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    // untuk mengupdate Data
    fun update(id: String, values: ContentValues): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    // untuk menghapus data
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$_ID'", null)
    }
}