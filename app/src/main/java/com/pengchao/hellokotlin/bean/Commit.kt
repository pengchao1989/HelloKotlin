package com.pengchao.hellokotlin.bean

/**
 * Created by pengchao on 2017/9/4.
 */
class CommitInfo(
    var commit:Commit,
    var author: Author
)

class Commit(
    var url: String,
    var message : String
)

class Author(
    var name : String,
    var date :String,
    var email:String
)