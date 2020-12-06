package cadenas.rene.hugotest.tasks.task5.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey var id: Int? = null,
    var name: String = "",
    var surName: String = ""
): Parcelable