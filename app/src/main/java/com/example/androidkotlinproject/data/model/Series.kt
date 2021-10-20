import com.google.gson.annotations.SerializedName

data class Series (

	@SerializedName("available") val available : Int,
	@SerializedName("collectionURI") val collectionURI : String
)