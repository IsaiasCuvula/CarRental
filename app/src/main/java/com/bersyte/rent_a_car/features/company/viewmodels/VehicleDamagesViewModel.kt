package com.bersyte.rent_a_car.features.company.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.features.company.data.models.UpdateDamageRequest
import com.bersyte.rent_a_car.features.company.data.models.VehicleDamage
import com.bersyte.rent_a_car.features.company.data.repositories.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class VehicleDamagesViewModel @Inject constructor(
    private val repository: CompanyRepository
) : ViewModel() {

    private val _damages = MutableStateFlow<List<VehicleDamage>>(emptyList())
    val damages = _damages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadDamages(onError = {})
    }

    private fun loadDamages(onError:(String)-> Unit){
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.fetchAllDamages()
                Log.d("✅ FETCH_CAR_DAMAGE", "✅ $response")
                _damages.value = response
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.e("❌ FETCH_CAR_DAMAGE", "❌ Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.e("❌ FETCH_CAR_DAMAGE", "❌ EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }finally {
                _isLoading.value = false
            }
        }
    }


    fun updateDamage(
        request: UpdateDamageRequest,
        onSuccess: (VehicleDamage?)-> Unit,
        onError:(String)-> Unit
    ) {
        viewModelScope.launch {
            try {
                Log.d("✅ UPDATE_CAR_DAMAGE", "✅ Data: $request")
                val response = repository.payDamage(request)
                Log.d("✅ UPDATE_CAR_DAMAGE", "✅ $response")
                loadDamages {  }
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.e("❌ UPDATE_CAR_DAMAGE", "❌ Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.e("❌ UPDATE_CAR_DAMAGE", "❌ EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }
}
