package com.example.wikipedia.Repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import javax.inject.Inject


class ArticleDatabaseOpenHelper @Inject constructor(context: Context) : ManagedSQLiteOpenHelper(context,"ArticlesDatabase.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("Favorites",true,"id" to INTEGER + PRIMARY_KEY, "title" to TEXT, "url" to TEXT, "thumbnailJson" to TEXT)

        db?.createTable("History",true,"id" to INTEGER + PRIMARY_KEY, "title" to TEXT, "url" to TEXT, "thumbnailJson" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}