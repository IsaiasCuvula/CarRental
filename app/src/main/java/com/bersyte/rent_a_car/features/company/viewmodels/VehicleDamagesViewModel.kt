package com.bersyte.rent_a_car.features.company.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.features.company.data.models.FixDamageRequest
import com.bersyte.rent_a_car.features.company.data.models.PayDamageRequest
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

    fun loadDamages(onError:(String)-> Unit){
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
            }
        }
    }


    fun markDamageAsFixed(
        request: FixDamageRequest,
        onSuccess: (VehicleDamage?)-> Unit,
        onError:(String)-> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = repository.markDamageAsFixed(request)
                Log.d("✅ FIX_CAR_DAMAGE", "✅ $response")
                loadDamages {  }
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.e("❌ FIX_CAR_DAMAGE", "❌ Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.e("❌ FIX_CAR_DAMAGE", "❌ EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }

    fun payDamage(
        request: PayDamageRequest,
        onSuccess: (VehicleDamage?)-> Unit,
        onError:(String)-> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = repository.payDamage(request)
                Log.d("✅ PAY_CAR_DAMAGE", "✅ $response")
                loadDamages {  }
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.e("❌ PAY_CAR_DAMAGE", "❌ Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.e("❌ PAY_CAR_DAMAGE", "❌ EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }
}
