package com.example.hastanerandevusistemi.ui.make_an_appointment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentMakeAppointmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakeAppointment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMakeAppointmentBinding
    private val makeAppointmentViewModel: MakeAppointmentViewModel by viewModels()
    private lateinit var citiesAdapter: CustomSpinnerAdapter
    private lateinit var districtsAdapter: CustomSpinnerAdapter
    private lateinit var hospitalsAdapter: CustomSpinnerAdapter
    private lateinit var departmentsAdapter: CustomSpinnerAdapter
    private lateinit var doctorsAdapter: CustomSpinnerAdapter
    private lateinit var daysAdapter: CustomSpinnerAdapter
    private lateinit var hoursAdapter: CustomSpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakeAppointmentBinding.inflate(layoutInflater)
        initView()
        getCityList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        makeAppointmentViewModel.getUserInfo(
            arguments?.getInt("tc")!!,
            arguments?.getString("password")!!
        )
        binding.confirmButton.setOnClickListener(this)
        binding.filterCloseButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.confirmButton.id -> {
                observeSelectedAppointment()

            }
            binding.filterCloseButton.id -> {
                findNavController().navigate(R.id.action_makeAppointment_to_home2)
            }
        }
    }

    private fun observeSelectedAppointment() {
        makeAppointmentViewModel.selectedCityId.observe(viewLifecycleOwner) { cityId ->
            if (cityId != null) {
                Log.d("selectedCityId", cityId.toString())
                makeAppointmentViewModel.selectedHospitalId.observe(viewLifecycleOwner) { hospitalId ->
                    if (hospitalId != null) {
                        Log.d("selectedHospitalId", hospitalId.toString())
                        makeAppointmentViewModel.selectedDepertmantId.observe(viewLifecycleOwner) { departmentId ->
                            if (departmentId != null) {
                                Log.d("selectedDepartmentId", departmentId.toString())
                                makeAppointmentViewModel.selectedDoctorId.observe(viewLifecycleOwner) { doctorId ->
                                    if (doctorId != null) {
                                        Log.d("selectedDoctorId", doctorId.toString())
                                        makeAppointmentViewModel.selectedDateId.observe(
                                            viewLifecycleOwner
                                        ) { dateId ->
                                            if (dateId != null) {
                                                Log.d("selectedDayId", dateId.toString())
                                                makeAppointmentViewModel.selectedHourId.observe(
                                                    viewLifecycleOwner
                                                ) { hourId ->
                                                    if (hourId != null) {
                                                        Log.d("selectedHourId", hourId.toString())
                                                        makeAppointmentViewModel.randevuAl(
                                                            makeAppointmentViewModel.userId.value!!,
                                                            cityId,
                                                            makeAppointmentViewModel.selectedCityName.value!!,
                                                            hospitalId,
                                                            makeAppointmentViewModel.selectedHospitalName.value!!,
                                                            departmentId,
                                                            makeAppointmentViewModel.selectedDepertmantName.value!!,
                                                            doctorId,
                                                            makeAppointmentViewModel.selectedDoctorName.value!!,
                                                            dateId,
                                                            makeAppointmentViewModel.selectedDateName.value!!,
                                                            hourId,
                                                            makeAppointmentViewModel.selectedHourName.value!!
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Toast.makeText(
            requireContext(),
            "Randevu alındı",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getCityList() {
        makeAppointmentViewModel.getCityData()
        makeAppointmentViewModel.city.observe(viewLifecycleOwner) {
            it?.let {
                citiesAdapter = CustomSpinnerAdapter(requireContext(), it)
                citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.citySpinner.adapter = citiesAdapter
            binding.citySpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    it?.get(position)?.id?.let { it1 -> setDistricts(it1) }
                    makeAppointmentViewModel.selectedCityId.value = it?.get(position)?.id
                    makeAppointmentViewModel.selectedCityName.value = it?.get(position)?.name

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
                }

            }
        }
    }

    private fun setDistricts(ilId: Int) {
        makeAppointmentViewModel.getDistrictData(ilId)
        makeAppointmentViewModel.district.observe(viewLifecycleOwner) { districtEntityList ->
            districtEntityList.let { district ->
                district?.forEach {
                    if (it.ilId == ilId) {
                        districtsAdapter = CustomSpinnerAdapter(requireContext(), district)
                        districtsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                districtsAdapter =
                    district?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                districtsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.discSpinner.adapter = districtsAdapter
            binding.discSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    districtEntityList?.get(position)?.value?.let { it1 -> setHospital(it1) }
                    makeAppointmentViewModel.selectedDistrictId.value =
                        districtEntityList?.get(position)?.value
                    makeAppointmentViewModel.selectedDistrictName.value =
                        districtEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
                }

            }
        }
    }

    private fun setHospital(discId: Int) {
        makeAppointmentViewModel.getHospitalData(discId)
        makeAppointmentViewModel.hospital.observe(viewLifecycleOwner) { hospitalEntityList ->
            hospitalEntityList.let { hospital ->
                hospital?.forEach {
                    if (it.ilceId == discId) {
                        hospitalsAdapter = CustomSpinnerAdapter(requireContext(), hospital)
                        hospitalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                hospitalsAdapter =
                    hospital?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                hospitalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.hospitalSpinner.adapter = hospitalsAdapter
            binding.hospitalSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    hospitalEntityList?.get(position)?.value?.let { it1 -> setDepartment(it1) }
                    makeAppointmentViewModel.selectedHospitalId.value =
                        hospitalEntityList?.get(position)?.value
                    makeAppointmentViewModel.selectedHospitalName.value =
                        hospitalEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setDepartment(hospitalId: Int) {
        makeAppointmentViewModel.getDepartmentData(hospitalId)
        makeAppointmentViewModel.depertmant.observe(viewLifecycleOwner) { departmentEntityList ->
            departmentEntityList.let { department ->
                department?.forEach {
                    if (it.hastaneId == hospitalId) {
                        departmentsAdapter = CustomSpinnerAdapter(requireContext(), department)
                        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                departmentsAdapter =
                    department?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.polyclinicSpinner.adapter = departmentsAdapter
            binding.polyclinicSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    departmentEntityList?.get(position)?.value?.let { it1 -> setDoctor(it1) }
                    makeAppointmentViewModel.selectedDepertmantId.value =
                        departmentEntityList?.get(position)?.value
                    makeAppointmentViewModel.selectedDepertmantName.value =
                        departmentEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setDoctor(departmentId: Int) {
        makeAppointmentViewModel.getDoctorData(departmentId)
        makeAppointmentViewModel.doctor.observe(viewLifecycleOwner) { doctorEntityList ->
            doctorEntityList.let { doctor ->
                doctor?.forEach {
                    if (it.poliklinikId == departmentId) {
                        doctorsAdapter = CustomSpinnerAdapter(requireContext(), doctor)
                        doctorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                doctorsAdapter =
                    doctor?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                doctorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.doctorSpinner.adapter = doctorsAdapter
            binding.doctorSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    doctorEntityList?.get(position)?.value?.let { it1 -> setDay(it1) }
                    makeAppointmentViewModel.selectedDoctorId.value =
                        doctorEntityList?.get(position)?.value
                    makeAppointmentViewModel.selectedDoctorName.value =
                        doctorEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setDay(doctorId: Int) {
        makeAppointmentViewModel.getDayData(doctorId)
        makeAppointmentViewModel.date.observe(viewLifecycleOwner) { dayEntityList ->
            dayEntityList.let { day ->
                day?.forEach {
                    if (it.doktorId == doctorId) {
                        daysAdapter = CustomSpinnerAdapter(requireContext(), day)
                        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                daysAdapter =
                    day?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.dateSpinner.adapter = daysAdapter
            binding.dateSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    dayEntityList?.get(position)?.value?.let { it1 -> setHour(it1) }
                    makeAppointmentViewModel.selectedDateId.value =
                        dayEntityList?.get(position)?.value
                    makeAppointmentViewModel.selectedDateName.value =
                        dayEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    fun setHour(dayId: Int) {
        makeAppointmentViewModel.getHourData(dayId)
        makeAppointmentViewModel.hour.observe(viewLifecycleOwner) { hourEntityList ->
            hourEntityList.let { hour ->
                hour?.forEach {
                    if (it.gunId == dayId) {
                        hoursAdapter = CustomSpinnerAdapter(requireContext(), hour)
                        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                hoursAdapter =
                    hour?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.hourSpinner.adapter = hoursAdapter
            binding.hourSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    hourEntityList?.get(position)?.value?.let { it1 ->
                        Log.d("TAG", "onItemSelected:  $it1")
                        makeAppointmentViewModel.selectedHourId.value = it1
                        makeAppointmentViewModel.selectedHourName.value =
                            hourEntityList[position].text
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }
}