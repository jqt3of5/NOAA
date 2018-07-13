package com.example.jqt3of5.noaa.Repository.Api.DataObjects

import java.util.*

class BlogPosts(val items : List<BlogPost>)
class BlogPost(val published : Date, val url : String, val selflink : String, val title : String, val content : String)
