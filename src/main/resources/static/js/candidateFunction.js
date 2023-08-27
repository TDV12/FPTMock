$(document).ready(function() {
	//Submit data create Candidate
	$(document).on("click", "#submitCreateCandidate", function() {
		let isValid = true;
		const errorItems = document.getElementsByClassName('error');
		for (let i = 0; i < errorItems.length; i++) {
			errorItems[i].style.display = 'none';

		}
		const applicationDate = document.forms['createCandidate']['applicationtDate'].value;
		const channel = document.forms['createCandidate']['channelId'].value;
		const location = document.forms['createCandidate']['locationId'].value;
		
		const fullName = document.forms['createCandidate']['fullName'].value;
		const gender = document.forms['createCandidate']['gender'].value;
		const dateOfBirth = document.forms['createCandidate']['dateOfBirth'].value;

		const university = document.forms['createCandidate']['universityId'].value;
		const universityOther = document.forms['createCandidate']['universityNameOther'].value;

		const faculty = document.forms['createCandidate']['facultyId'].value;
		const facultyOther = document.forms['createCandidate']['facultyNameOther'].value;
		const phone = document.forms['createCandidate']['phone'].value;
		const email = document.forms['createCandidate']['email'].value;
		const skill = document.forms['createCandidate']['skill'].value;
		const gradurationYear = document.forms['createCandidate']['gradurationYear'].value;
		const foreignLanguage = document.forms['createCandidate']['foreignLanguage'].value;
		const level = document.forms['createCandidate']['level'].value;

		console.log(university);
		if (!applicationDate) {
			displayError('applicationDateValidMess', 'ApplicationDate is required!');
			isValid = false;

		}
		if (!channel || (channel == 'none')) {
			displayError('errorChannel', 'Channel is required!');
			isValid = false;

		}
		if (!location || (location == 'none')) {
			displayError('errorSite', 'Site is required!');
			isValid = false;

		}
	
		if (!fullName) {
			displayError('errorName', 'Name is required!');
			isValid = false;

		}
		if (!gender) {
			displayError('errorGender', 'Gender is required!');
			isValid = false;

		}
		if (!dateOfBirth) {
			displayError('dateOfBirthValidMess', 'DateOfBirth is required!');
			isValid = false;

		}

		if (!university || university == 'none') {
			displayError('errorUniversity', 'University is required!');
			isValid = false;

		}
		if (!universityOther && (university == 0) ) {
			displayError('errorUniversity', 'University is required!');
			isValid = false;

		}

		if (!faculty || (faculty == 'none')) {
			displayError('errorFaculty', 'Faculty is required!');
			isValid = false;

		}
		if (!facultyOther && (faculty == 0)) {
			displayError('errorFaculty', 'Faculty is required!');
			isValid = false;

		}
		if (!phone) {
			displayError('errorPhone', 'Phone is required!');
			isValid = false;

		} else if (!isValidPhone(phone)) {
			displayError('errorPhone', 'Phone is invalid format!');
			isValid = false;
		}
		if (!email) {
			displayError('errorEmail', 'Email is required!');
			isValid = false;

		}else if (!isValidEmail(email)) {
		displayError('errorEmail', 'Email is invalid format!');
		isValid = false;
		}
		
		
		if (!skill) {
			displayError('errorSkill', 'Skill is required!');
			isValid = false;

		}
		if (!gradurationYear) {
			displayError('gradurationYearValidMess', 'GradurationYear is required!');
			isValid = false;

		}
		if (!foreignLanguage) {
			displayError('errorforeignLanguage', 'Foreign Language is required!');
			isValid = false;

		}

		if (!level) {
			displayError('errorLevel', 'Level is required!');
			isValid = false;

		}


		if (isValid) {
			let generalForm = $("#formData2").serializeArray();
			let dataFormGeneral = new FormData();
			$.each(generalForm, function(i, v) {
				dataFormGeneral.append(v.name, v.value);
			})
			let file = $("#CV")[0].files[0];
			if (file !== undefined) {
				dataFormGeneral.append("CV", file);
			}
			$.post({
				url: "/createNewCandidate",
				data: dataFormGeneral,
				processData: false,
				contentType: false,
				success: function(res) {
					//                window.location.href = res;
					$("#view").html(res);
				}, error: function(request, status, error) {
					$("#subview").html(request.responseText);
				}
			})
		}

	})

	$(document).on("click", "#updateCandidate", function() {
		let emplId = $("#emplId").val();
		let numberId = parseInt(emplId);
		console.log(numberId);

		$.get({
			url: `/updateCandidate/${numberId}`,
			success: function(res) {
				$("#view").html(res);
			},
			error: function() {
				console.log("error");
			},
		});

	})

	$(document).on("click", "#submitUpdateCandidate", function() {

		let isValid = true;
		const errorItems = document.getElementsByClassName('error');
		for (let i = 0; i < errorItems.length; i++) {
			errorItems[i].style.display = 'none';

		}
		const applicationDate = document.forms['updateCandidate']['applicationtDate'].value;
		const channel = document.forms['updateCandidate']['channelId'].value;
		const location = document.forms['updateCandidate']['locationId'].value;
		const account = document.forms['updateCandidate']['account'].value;
		const fullName = document.forms['updateCandidate']['fullName'].value;
		const gender = document.forms['updateCandidate']['gender'].value;
		const dateOfBirth = document.forms['updateCandidate']['dateOfBirth'].value;
		
		const university = document.forms['updateCandidate']['universityId'].value;
		const universityOther = document.forms['updateCandidate']['universityNameOther'].value;
		const faculty = document.forms['updateCandidate']['facultyId'].value;
		console.log(faculty);
		const facultyOther = document.forms['updateCandidate']['facultyNameOther'].value;
		const phone = document.forms['updateCandidate']['phone'].value;
		const email = document.forms['updateCandidate']['email'].value;
		const skill = document.forms['updateCandidate']['skill'].value;
		const gradurationYear = document.forms['updateCandidate']['gradurationYear'].value;
		const foreignLanguage = document.forms['updateCandidate']['foreignLanguage'].value;
		const level = document.forms['updateCandidate']['level'].value;

		
		if (!applicationDate) {
			displayError('applicationDateValidMess', 'ApplicationDate is required!');
			isValid = false;

		}
		if (!channel || (channel == 'none')) {
			displayError('errorChannel', 'Channel is required!');
			isValid = false;

		}
		if (!location || (location == 'none')) {
			displayError('errorSite', 'Site is required!');
			isValid = false;

		}
		if (!account) {
			displayError('errorAccount', 'Account is required!');
			isValid = false;

		}
		if (!fullName) {
			displayError('errorName', 'Name is required!');
			isValid = false;

		}
		if (!gender) {
			displayError('errorGender', 'Gender is required!');
			isValid = false;

		}
		if (!dateOfBirth) {
			displayError('dateOfBirthValidMess', 'DateOfBirth is required!');
			isValid = false;

		}

		if (!university || (university == 'none')) {
			displayError('errorUniversity', 'University is required!');
			isValid = false;

		}
		if (!universityOther  && (university == 0) ) {
			displayError('errorUniversity', 'University is required!');
			isValid = false;

		}

		if (!faculty || (faculty == 'none')) {
			displayError('errorFaculty', 'Faculty is required!');
			isValid = false;

		}
		if (!facultyOther && (faculty == 0)) {
			displayError('errorFaculty', 'Faculty is required!');
			isValid = false;

		}
		if (!phone) {
			displayError('errorPhone', 'Phone is required!');
			isValid = false;

		}else if (!isValidPhone(phone)) {
			displayError('errorPhone', 'Phone is invalid format!');
			isValid = false;
		}
		if (!email) {
			displayError('errorEmail', 'Email is required!');
			isValid = false;

		}else if (!isValidEmail(email)) {
		displayError('errorEmail', 'Email is invalid format!');
		isValid = false;
	}
		if (!skill) {
			displayError('errorSkill', 'Skill is required!');
			isValid = false;

		}
		if (!gradurationYear) {
			displayError('gradurationYearValidMess', 'GradurationYear is required!');
			isValid = false;

		}
		if (!foreignLanguage) {
			displayError('errorforeignLanguage', 'Foreign Language is required!');
			isValid = false;

		}

		if (!level) {
			displayError('errorLevel', 'Level is required!');
			isValid = false;

		}


		if (isValid) {
			let generalForm = $("#formData3").serializeArray();
			let dataFormGeneral = new FormData();
			$.each(generalForm, function(i, v) {
				dataFormGeneral.append(v.name, v.value);
			})
			console.log(1);
			let file = $("#CV")[0].files[0];
			if (file !== undefined) {
				dataFormGeneral.append("CV", file);
			}
			$.post({
				url: "/updateOneCandidate",
				data: dataFormGeneral,
				processData: false,
				contentType: false,
				success: function(res) {
					//                window.location.href = "http://localhost:8080/home#CandidateManagement";
					$("#view").html(res);
				}, error: function(request, status, error) {
					$("#subview").html(request.responseText);
				}
			})
		}


	})

	$(document).on("click", "#submitUpdateResultCandidate", function() {
		let generalForm = $("#formData4").serializeArray();
		let dataFormGeneral = new FormData();
		$.each(generalForm, function(i, v) {
			dataFormGeneral.append(v.name, v.value);
		})
		console.log(1);
		$.post({
			url: "/updateResultCandidate",
			data: dataFormGeneral,
			processData: false,
			contentType: false,
			success: function(res) {
				//                window.location.href = "http://localhost:8080/home#CandidateManagement";
				$("#view").html(res);
			}, error: function(request, status, error) {
				$("#subview").html(request.responseText);
			}
		})

	})

	$(document).on('click', "#searchButtonCandidate", function() {
		let emplIdSearch = $("#emplIdSearch").val();
		let accountSearch = $("#accountSearch").val();
		let nameSearch = $("#nameSearch").val();
		let DOBSearch = $("#DOBSearch").val();
		let phoneSearch = $("#phoneSearch").val();
		let emailSearch = $("#emailSearch").val();
		let sizePage = $("#sizePageSelect").val();

		$.get({
			url: `/candidateListing/?emplId=${emplIdSearch}&&sizePage=${sizePage}&&account=${accountSearch}&&fullName=${nameSearch}&&dateOfBirth=${DOBSearch}&&phone=${phoneSearch}&&email=${emailSearch}`,
			success: function(res) {
				$("#view").html(res);
			},
			error: function(res) {
				console.log(res)
			}
		})
	})

	$(document).on('click', "#chooseOtherFileCandidate", function() {
		$("#CV").removeClass("d-none");
		$("#fileName").addClass("d-none");
		$("#chooseOtherFileCandidate").addClass("d-none");
	})

	$(document).on("click", "#deleteCandidate", function() {
		let generalForm = $("#formData").serializeArray();
		let dataFormGeneral = new FormData();
		$.each(generalForm, function(i, v) {
			dataFormGeneral.append(v.name, v.value);
		})

		$.post({
			url: "/deleteOneCandidate",
			data: dataFormGeneral,
			processData: false,
			contentType: false,
			success: function(res) {
				$("#view").html(res);
			},
			error: function() {
				console.log("error");
			},
		});

	})

	$(document).on("click", "#deleteMutilCandidate", function() {
		let selectedItem = new Array();
		$('input[name="Item"]:checked').each(function() {
			selectedItem.push(this.value);
		});
		if (selectedItem.length == 0) {
			alert("Vui lòng chọn một phần tử");
		} else {
			let formData = new FormData();
			formData.append("emplId", selectedItem);
			console.log(formData);

			$.post({
				url: "/deleteMutilCandidate",
				data: formData,
				processData: false,
				contentType: false,
				success: function(res) {
					$("#view").html(res);
				},
				error: function() {
					console.log("error");
				},
			});
		}


	})

	$(document).on("click", "#updateCandidateOnListing", function() {
		let selectedItem = new Array();

		$('input[name="Item"]:checked').each(function() {

			selectedItem.push(this.value);
			console.log(selectedItem.length);



		});

		if (selectedItem.length > 1) {
			alert("Không thể update nhiều hơn 1 Candidate");
		} else if (selectedItem.length == 0) {
			alert("Vui lòng chọn một phần tử");
		}
		else {
			let emplId = selectedItem[0];

			console.log(emplId);

			$.get({
				url: `/updateCandidate/${emplId}`,
				success: function(res) {
					$("#view").html(res);
				},
				error: function() {
					console.log("error");
				},
			});

		}



	})

	$(document).on("click", ".candidateClick", function() {
		let idElement = $(this).attr("id");
		let numberIndex = parseInt(idElement);
		console.log(idElement);
		$.get({
			url: `/viewCandidate/${numberIndex}`,
			success: function(res) {
				$("#view").html(res);
			},
			error: function() {
				console.log("error");
			},
		});
	})

	$(document).on("click", "#myTableEntryTest", function() {
		//        let budgetCode = $("#budgetCode").val();
		 let today = new Date();
		let time = today.getHours() + ":" + today.getMinutes();
		let i = $("#entryTestTable").children("tr").length
		$("#entryTestTable").append(`<tr id="${i}">
										<td scope="row"><a type="button" class="budgetTrash me-1" onclick="deleteRow(this);"><i class="fa-solid fa-trash-can"></i></a>
										</td>
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="entryTests[${i}].time" id="time${i}" value="${time}" readonly/>
										</td>
										<td><input type="date" class="form-control" aria-describedby="basic-addon3"
												name="entryTests[${i}].date" id="date${i}" />
										</td>
										<td><input type="text" class="form-control budgetUnitExpense text-nowrap" aria-describedby="basic-addon3"
												name="entryTests[${i}].languageValuator" id="languageValuator${i}"/></td>
												
										<td><input type="number" class="form-control budgetQuantity" aria-describedby="basic-addon3"
												name="entryTests[${i}].languageResult" id="languageResult${i}" min="0"/></td>
												
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="entryTests[${i}].technicalValuator" id="technicalValuator${i}" />
										</td>
										<td><input type="number" class="form-control budgetTax" aria-describedby="basic-addon3"
												name="entryTests[${i}].technicalResult" id="technicalResult${i}" min="0" />
										</td>
										<td><select name="entryTests[${i}].result" id="result${i}" class="form-control" aria-describedby="basic-addon3">
														<option value="TestPass">Test-Pass</option>
										        <option value="TestFail">Test-Fail</option>
													</select>
										
										</td>
										
										
									</tr>`);
	})

	$(document).on("click", "#myTableInterview", function() {
		//        let budgetCode = $("#budgetCode").val();
		let today = new Date();
		let time = today.getHours() + ":" + today.getMinutes();
		let i = $("#interviewTable").children("tr").length
		$("#interviewTable").append(`<tr id="${i}">
										<td scope="row"><a type="button" class="budgetTrash me-1" onclick="deleteRow(this);"><i class="fa-solid fa-trash-can"></i></a>
										</td>
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="interviews[${i}].time" id="time${i}" value="${time}" readonly/>
										</td>
										<td><input type="date" class="form-control" aria-describedby="basic-addon3"
												name="interviews[${i}].date" id="date${i}" />
										</td>
										<td><input type="text" class="form-control budgetUnitExpense text-nowrap" aria-describedby="basic-addon3"
												name="interviews[${i}].interviewer" id="interviewer${i}"/></td>
												
										<td><input type="text" class="form-control budgetQuantity" aria-describedby="basic-addon3"
												name="interviews[${i}].comments" id="comments${i}" min="0"/></td>
												
										<td><select name="interviews[${i}].result" id="result${i}" class="form-control" aria-describedby="basic-addon3">
														<option value="InterviewPass">Interview-Pass</option>
										        <option value="InterviewFail">Interview-Fail</option>
													</select>
									
										</td>
										
										
										
									</tr>`);
	})

	$(document).on("click", "#checkBoxAll", function() {
		$(".checkBoxItem").prop('checked', $(this).prop('checked'));
	})

	$(document).on("change", ".selectUniversity", function() {
		var element = document.getElementById('universityOther');
			
		
			if ($("#univer").val() == 0 ) {
				element.style.display = 'block';
				
			}else {
				element.style.display = 'none';
				
			}
		
	})

	$(document).on("change", ".selectFaculty", function() {
		var element = document.getElementById('facultyOther');

	if ($("#faculty").val() == 0 ) {
				element.style.display = 'block';
				
			}else {
				element.style.display = 'none';
				
			}
	})





})
const isValidEmail = function(emailLogin) {
	return /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(emailLogin);
}

const isValidPhone = function(phone) {
		return /((09|03|07|08|05)+([0-9]{8})\b)/g.test(phone);
	}


function deleteRow(el) {
	// while there are parents, keep going until reach TR
	while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
		el = el.parentNode;
	}
	el.parentNode.removeChild(el);

}
const displayError = function(id, message) {
	document.getElementById(id).innerText = message;
	document.getElementById(id).style.display = 'block'
}