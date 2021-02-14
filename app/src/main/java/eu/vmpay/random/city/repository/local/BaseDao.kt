package eu.vmpay.random.city.repository.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseDao<T> {
    /**
     * Insert an object in the database
     *
     * @param obj the object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database. If there is the row with the same primary key,
     * this object will be replaced
     *
     * @param obj the objects to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(obj: Array<T>): List<Long>

    /**
     * Update an object from the database in a synchronous way
     *
     * @param obj the object to be updated
     */
    @Update
    abstract suspend fun update(obj: T): Int

    /**
     * Update an object from the database in a synchronous way
     *
     * @param obj the object to be updated
     */
    @Update
    abstract fun updateSync(obj: T): Int

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    abstract suspend fun delete(obj: T): Int

    /**
     * Insert an object in the database. Ignores if there is the row with the same primary key,
     * the new object will not be inserted
     *
     * @param obj the object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertIgnore(obj: T): Long

    /**
     * Insert an object in the database. Ignores if there is the row with the same primary key,
     * the new object will not be inserted
     *
     * @param obj the object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertIgnoreSync(obj: T): Long
}
