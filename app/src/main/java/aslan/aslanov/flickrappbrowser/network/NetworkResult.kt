package aslan.aslanov.flickrappbrowser.network

data class NetworkResult<out R>(val status: StatusNetwork, val data: R?, val msg: String?) {
    companion object {
        fun <R> success(successData: R?): NetworkResult<R> {
            return NetworkResult(StatusNetwork.SUCCESS,successData,null)
        }
        fun <R> error(errorMessage:String,errorData:R?):NetworkResult<R>{
            return NetworkResult(StatusNetwork.ERROR,errorData,errorMessage)
        }

        fun <R>loading (loadingData:R?):NetworkResult<R>{
            return NetworkResult(StatusNetwork.LOADING,loadingData,null)
        }
    }
}