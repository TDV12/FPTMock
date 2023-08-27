$(document).ready(function () {
    //allow choose only one checkbox
    $(document).on('click', '.check-box', function () {
        $(".check-box").not(this).prop("checked", false);
    });

    $(document).on('click', "#search-button", function () {
        let locationId = $("#locationIdListing").val();
        let classNameId = $("#classNameId").val();
        let status = $("#status").val();
        let fromDate = $("#fromDate").val();
        let toDate = $("#toDate").val();
        $.get({
            url: "/classListing/Search?location=" + locationId + "&&className=" + classNameId + "&&status=" + status + "&&fromDate=" + fromDate + "&&toDate=" + toDate,
            success: function (res) {
                $("#classListingBody").html(res);
            },
            error: function (res) {
                console.log(res)
            }
        })
    })

    $(document).on("change", "#sizePage", function () {
        let locationId = $("#locationIdListing").val();
        let classNameId = $("#classNameId").val();
        let status = $("#status").val();
        let fromDate = $("#fromDate").val();
        let toDate = $("#toDate").val();
        let pageSize = $("#sizePage").val();
        $.get({
            url: "/classListing/Search?location=" + locationId + "&&className=" + classNameId + "&&status=" + status + "&&fromDate=" + fromDate + "&&toDate=" + toDate + "&&sizePage=" + pageSize,
            success: function (res) {
                $("#classListingBody").html(res);
            },
            error: function (res) {
                console.log(res)
            }
        })
    })

    $(document).on('click', "#chooseOtherFile", function () {
        $("#learningPath").removeClass("d-none");
        $("#fileName").addClass("d-none");
        $("#chooseOtherFile").addClass("d-none");
    })

    //Submit data create Class
    $(document).on("click", "#submitCreateClass", function () {
        validateData("#formData");
        let validDataForm = $("#formData").valid();
        let validDate = checkInputDate("#exceptedStartDate", "#exceptedEndDate", "#actualStartDate", "#actualEndDate");
        if (validDataForm && validDate) {
            submitData("/createNewClass", "#view", "#subview")
        }
    })


    $(document).on("click", "#cancelClass", function () {
        let classId = $('.check-box:checkbox:checked').val();
        getView(`/cancelClass/${classId}`)
    })

    $(document).on("click", "#updateClassListing", function () {
        let classCode = $('.check-box:checkbox:checked').parent().next().next().text()
        $.get({
            url: `/classInViewMode/${classCode}`,
            success: function (res) {
                $("#view").html(res);
                callApiGetLocationDetail();
                $(".updateClassInput").attr("readonly", false);
                $(".updateDropdown").prop("disabled", false);
                $("#chooseOtherFile").removeClass("disabled");
                $("#btn-general").trigger('click');
                $("#updateClass").attr("id", "updateClassClick")
            },
            error: function () {
                console.log("error get information of class")
            }
        })
    })

    $(document).on("click", "#submitUpdateClass", function () {
        let classCode = $("#classCode").val();
        confirmPopUp("/submitClass", classCode);
    })
    
    
    $(document).on("click", "#traineeImportFile", function () {
        var x = document.getElementById("textView");
        x.innerHTML = "Trainee";
    })
    $(document).on("click", "#traineeInformation", function () {
        var x = document.getElementById("textView");
        x.innerHTML = "Class";
    })

    $(document).on("click", "#approveClass", function () {
        let classCode = $("#classCode").val();
        confirmPopUp("/approveClass", classCode);
    })

    $(document).on("click", "#rejectClass", function () {
        changeClassStatus("/rejectClass");
    })

    $(document).on("click", "#acceptClass", function () {
        let classCode = $("#classCode").val();
        confirmPopUp("/acceptClass", classCode);
    })

    $(document).on("click", "#declineClass", function () {
        changeClassStatus("/declineClass");
    })

    $(document).on("click", "#startClass", function () {
        let classCode = $("#classCode").val();
        confirmPopUp("/startClass", classCode);
    })

    $(document).on("click", "#finishClass", function () {
        let classCode = $("#classCode").val();
        confirmPopUp("/finishClass", classCode);
    })

    //add Budget function
    $(document).on("click", "#myTableBudget", function () {
        let budgetCode = $("#budgetCode").val();
        let i = $("#budgetTable").children("tr").length
        $("#budgetTable").append(`<tr id="${i}">
										<td scope="row"><a type="button" class="budgetTrash" onclick="deleteRow(this);"><i class="fa-solid fa-trash-can"></i></a>
										</td>
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetItem" id="budgetItem${i}"/>
										</td>
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetUnit" id="budgetUnit${i}" />
										</td>
										<td><input type="number" class="form-control budgetUnitExpense" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetExpense" id="budgetUnitExpense${i}" min="0" /></td>
												
										<td><input type="number" class="form-control budgetQuantity" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetQuantity" id="budgetQuantity${i}" min="0"/></td>
												
										<td><input type="number" class="form-control" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetAmount" id="budgetAmount${i}" readonly/>
										</td>
										<td><input type="number" class="form-control budgetTax" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetTax" id="budgetTax${i}" min="0" />
										</td>
										<td><input type="number" class="form-control budgetSum" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetSum" id="budgetSum${i}" readonly/>
										</td>
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetNote" id="budgetNote${i}" />
										</td>
											<td class="d-none"><input type="hidden" class="form-control budgetCode" aria-describedby="basic-addon3"
												name="budgets[${i}].budgetCode" value=${budgetCode} />
										</td>
									</tr>`);
    })

    //add audit function
    $(document).on("click", "#auditTable", function () {
        let j = $("#auditTableBody").children("tr").length
        $("#auditTableBody").append(`<tr>
										<th scope="row"><a type="button" onclick="deleteRow(this);"><i class="fa-solid fa-trash-can"></i></a>
										</th>
										<td><input type="date" class="form-control" aria-describedby="basic-addon3"
												name="audits[${j}].eventDate" "/>
										</td>
										<td><select class="form-select" aria-label="Default select example"
                                                name="audits[${j}].eventCategory">
										        <option value="Trainer">Trainer</option>
										        <option value="Trainee">Trainee</option>
										        <option value="Courseware">Courseware</option>
										        <option value="Organization">Organization</option>
										        <option value="Logistics">Logistics</option>
										        <option value="Management">Management</option>
										        <option value="Calender">Calender</option>
										        <option  selected value="Other">Other</option>										
                                        </select>
										</td>
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="audits[${j}].relatedPartyPeople"/></td>
												
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="audits[${j}].action"/></td>
												
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="audits[${j}].pic"/>
										</td>
										<td><input type="date" class="form-control" aria-describedby="basic-addon3"
												name="audits[${j}].deadLine" />
										</td>
										<td><input type="text" class="form-control" aria-describedby="basic-addon3"
												name="audits[${j}].note"/>
										</td>
									</tr>`);
    })

    //view mode class function
    $(document).on("click", ".viewMode", function () {
        let code = $(this).text();
        $.get({
            url: `classInViewMode/${code}`,
            success: function (res) {
                $("#view").html(res);
                callApiGetLocationDetail();
            },
            error: function () {
                console.log("error get information of class")
            }
        })
    })

    //update class funtion (remove readonly & disabled)
    $(document).on("click", "#updateClass", function () {
        $(this).attr("id", "updateClassClick")
        $(".updateClassInput").attr("readonly", false);
        $(".updateDropdown").prop("disabled", false);
        $("#chooseOtherFile").removeClass("disabled");
        $("#btn-general").trigger('click');
    })


    //submit data update class
    $(document).on("click", "#updateClassClick", function () {
        $(".disable").prop("disabled", false);
        $(".readonly").prop("readonly", false);
        validateData("#formData");
        let validDataForm = $("#formData").valid();
        let validDate = checkInputDate("#exceptedStartDate", "#exceptedEndDate", "#actualStartDate", "#actualEndDate")
        if (validDataForm && validDate) {
            submitData("/updateClass", "#view", "#view")
        }
    })

    //add budget code for add budget function
    $(document).on("change", "#budgetCode", function () {
        let code = $("#budgetCode").val();
        $(".budgetCode").each(function () {
            $(this).val(code)
        })
    })

    //caculated budget sum
    $(document).on("change", ".budgetQuantity,.budgetUnitExpense", function () {
        let index = $(this).attr("id").replace(/[^0-9.]/g, "");
        let budgetExpen = $(`#budgetUnitExpense${index}`).val();
        let budgetQuantity = $(this).val();
        $(`#budgetAmount${index}`).val((budgetExpen * budgetQuantity).toFixed(3));
        if ($(`#budgetTax${index}`).val()) {
            let budgetAmount = Number($(`#budgetAmount${index}`).val())
            let tax = ($(`#budgetTax${index}`).val()) / 100;
            $(`#budgetSum${index}`).val((budgetAmount + (tax * budgetAmount)).toFixed(3));
        }
    })

    $(document).on("change", ".budgetTax", function () {
        let index = $(this).attr("id").replace(/[^0-9.]/g, "");
        let tax = $(this).val() / 100;
        let budgetAmount = Number($(`#budgetAmount${index}`).val());
        $(`#budgetSum${index}`).val((budgetAmount + (tax * budgetAmount)).toFixed(3));
    })

    $(document).on("change", ".budgetTax,.budgetQuantity,.budgetUnitExpense", function () {
        sumCaculated();
    })

    $(document).on("click", ".budgetTrash", function () {
        sumCaculated();
    })

    $(document).on("click", "#search-button", function () {
        // getCheckedCheckBox();
    })

})

//delete row function
function deleteRow(el) {
    // while there are parents, keep going until reach TR
    while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
        el = el.parentNode;
    }
    el.parentNode.removeChild(el);
}

//cacutaled total and over budget
const sumCaculated = function () {
    let sum = 0;
    let estimatedBudget = Number($("#estimatedBudget").val())
    $(".budgetSum").each(function () {
        let id = $(this).attr("id");
        let index = id.replace(/[^0-9.]/g, "");
        sum = sum + Number($(`#budgetSum${index}`).val())
    })
    $("#total").val(sum.toFixed(3))
    let total = Number($("#total").val())
    if ((estimatedBudget - total) > 0) {
        $("#overBudget").val(0)
    } else {
        $("#overBudget").val(estimatedBudget - total)
    }
}

//change status of class function
const changeClassStatus = function (url) {
    let classCode = $("#classCode").val();
    let dataForm = new FormData();
    dataForm.append("classCode", classCode);
    $.confirm({
        title: 'Remarks!',
        content: '' +
            '<form action="#" class="formName">' +
            '<div class="form-group">' +
            '<label>Enter something here</label>' +
            '<input type="text" placeholder="Enter Reason" class="name form-control" required />' +
            '</div>' +
            '</form>',
        buttons: {
            formSubmit: {
                text: 'Submit',
                btnClass: 'btn-blue',
                action: function () {
                    let reason = this.$content.find('.name').val();
                    if (!reason) {
                        $.alert('Enter Reason');
                        return false;
                    }
                    dataForm.append("remarks", reason);
                    $.post({
                        url: `${url}`,
                        processData: false,
                        contentType: false,
                        data: dataForm,
                        success: function (res) {
                            $("#view").html(res);
                        },
                        error: function (request, status, error) {
                            $("#view").html(request.responseText);
                        }
                    })
                }
            },
            cancel: function () {
            },
        },
        onContentReady: function () {
            let jc = this;
            this.$content.find('form').on('submit', function (e) {
                e.preventDefault();
                jc.$$formSubmit.trigger('click'); // reference the button and click it

            });
        }
    });
}

//get data from form and submit
const submitData = function (url, viewSuccess, viewError) {
    let generalForm = $("#formData").serializeArray();
    let dataFormGeneral = new FormData();
    $.each(generalForm, function (i, v) {
        dataFormGeneral.append(v.name, v.value);
    })
    let fileLearningPath = $("#learningPath")[0].files[0];
    if (fileLearningPath !== undefined) {
        dataFormGeneral.append("learningPath", fileLearningPath);
    }
    $.confirm({
        title: 'Confirm!',
        content: 'Are you sure to submit ?',
        buttons: {
            Ok: function () {
                $.post({
                    url: `${url}`,
                    data: dataFormGeneral,
                    processData: false,
                    contentType: false,
                    success: function (res) {
                        $(`${viewSuccess}`).html(res);
                    }, error: function (request, status, error) {
                        $(`${viewError}`).html(request.responseText);
                    }
                })
            },
            Cancel: function () {
                $.alert('Canceled!');
            }
        }
    });
}

//confirmPopUp
const confirmPopUp = function (url, value) {
    $.confirm({
        title: 'Confirm!',
        content: 'Are you sure to confirm!',
        buttons: {
            confirm: function () {
                $.get({
                    url: `${url}/${value}`,
                    success: function (res) {
                        $("#view").html(res);
                    },
                    error: function (request, status, error) {
                        $("#view").html(request.responseText);
                    }
                })
            },
            cancel: function () {
                $.alert('Canceled!');
            },

        }
    });
}

//
