package com.example.hastanerandevusistemi.ui.make_an_appointment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
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
        binding.confirmButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.confirmButton.id -> {
                makeAppointmentViewModel.randevuAl()
            }
        }
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO()
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO()
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO()
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO()
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO()
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
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO()
                }

            }
        }
    }
}