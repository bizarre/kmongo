/*
 * Copyright (C) 2016/2020 Litote
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.litote.kmongo.coroutine

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.litote.kmongo.model.Friend
import java.util.concurrent.ConcurrentHashMap
import kotlin.test.assertEquals

/**
 *
 */
class ForEachTest : KMongoCoroutineBaseTest<Friend>() {

    @Test
    fun canFindOne() = runBlocking {
        col.insertMany(
            listOf(
                Friend("John", "22 Wall Street Avenue"),
                Friend("Fred", "11 Wall Street Avenue")
            )
        )
        val map = ConcurrentHashMap<String, Friend>()
        col.find().forEach {
            map[it.name!!] = it
        }
        assertEquals(2, map.size)
        assertEquals("22 Wall Street Avenue", map["John"]?.address)
        assertEquals("11 Wall Street Avenue", map["Fred"]?.address)
    }
}