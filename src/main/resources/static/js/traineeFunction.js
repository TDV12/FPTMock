let trainees = []
$(document).ready(function () {

    $(document).on("click", "#import", function () {
        let formData = new FormData();
        let fileTrainee = $("#fileTrainee")[0].files[0];
        let classCode = $("#classCode").val();
        formData.append("classCode", classCode);
        if (fileTrainee != undefined) {
            formData.append("multipartFile", fileTrainee);
            $.post({
                url: "/importFileTrainee",
                data: formData,
                processData: false,
                contentType: false,
                success: function (res) {
                    $("#view").html(res);
                    $(".modal-backdrop").removeClass("modal-backdrop");
                    $(".modal-open").removeAttr("style");
                    $("#traineeImportFile").trigger("click");
                },
                error: function (request, status, error) {
                    $("#view").html(request.responseText);
                    $(".modal-backdrop").removeClass("modal-backdrop");
                    $(".modal-open").removeAttr("style");
                }
            })
        }
    })

    $(document).on("click", "#addTraineeConfirmBtn", function () {
        let value = [];
        let classCode = $("#classCode").val();
        $("#listTraineeAddTrainee input:checked").each(function () {
            value.push(this.id)
        })
        if (value.length > 0) {
            $.post({
                url: `/addTraineeInClass/${classCode}`,
                data: JSON.stringify(value),
                dataType: 'json',
                contentType: 'application/json',
                success: function () {
                    $("#view").html(res);
                    $("#traineeImportFile").trigger("click");
                    $(".modal-backdrop").removeClass("modal-backdrop");
                    $(".modal-open").removeAttr("style");
                },
                error: function (request) {
                    $("#view").html(request.responseText);
                    $("#traineeImportFile").trigger("click");
                    $(".modal-backdrop").removeClass("modal-backdrop");
                    $(".modal-open").removeAttr("style");
                    console.log("error in add trainee")
                }
            })
        }
    })

    $(document).on("change", "#checkAll", function () {
        $("#listTrainee input:checkbox").prop("checked", this.checked);
    })
    $(document).on("change", "#checkAllAddTrainee", function () {
        $("#listTraineeAddTrainee input:checkbox").prop("checked", this.checked);
    })

    $(document).on("click", "#updateTrainee", function () {
        trainees = [];
        $("#informationTraineeBody").html("");
        $("#listTrainee input:checked").each(function () {
            let id = $(this).parent().next().next().text();
            let name = $(this).parent().next().next().next().next().text();
            let trainee = {
                id: id, name: name, status: "Active"
            };
            trainees.push(trainee);
        })
        trainees.forEach(function (value, index) {
                $("#informationTraineeBody").append(
                    `<tr name="list">
                        <td class="filterable-cell">${value.id}</td>
                        <td class="filterable-cell">${value.name}</td>
                        <td class="filterable-cell">
                         <input type="hidden" name="emplId" value=${value.id}>
                            <select class=" w-75 selected" id="status" name="status" onchange="collectStatus(this,${value.id})">
                                <option selected value="Active">Active</option>
                                <option value="Deferred">Deferred</option>
                                <option value="DropOut">Drop-out</option>
                            </select>
                        </td>
                    </tr>`
                )
            }
        )
    })

    $(document).on("click", "#submitUpdateStatusTrainee", function () {
        let classCode = $("#classCode").val();
        console.log(trainees)
        $.post({
            url: `/traineeStatusChange/${classCode}`,
            data: JSON.stringify(trainees),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                $("#view").html(res);
                $(".modal-backdrop").removeClass("modal-backdrop");
                $(".modal-open").removeAttr("style");
                $("#traineeImportFile").trigger("click");
            }
        })

    })

    $(document).on("click", "#searchTrainee", function () {
        let classCode = $("#classCode").val();
        let emplId = $("#emplId").val();
        let emplAccount = $("#emplAccount").val();
        let emplName = $("#emplName").val();
        let emplDoB = $("#emplDoB").val();
        let emplPhone = $("#emplPhone").val();
        let emplEmail = $("#emplEmail").val();
        $.get({
            url: `/trainee/Search/${classCode}?emplId=${emplId}&&emplAccount=${emplAccount}&&emplName=${emplName}&&emplDoB=${emplDoB}&&emplPhone=${emplPhone}&&emplEmail=${emplEmail}`,
            success: function (res) {
                $("#view").html(res);
                $("#traineeImportFile").trigger("click");
                appendDataSearch(emplId, emplAccount, emplName, emplDoB, emplPhone, emplEmail);
            },
            error: function (request, status, error) {
                $("#view").html(request.responseText);
                console.log("pagingError")
            },
        });
    })

    $(document).on("click", "#searchTraineeDB", function () {
        let classCode = $("#classCode").val();
        let emplId = $("#emplId1").val();
        let emplAccount = $("#emplAccount1").val();
        let emplName = $("#emplName1").val();
        let emplDoB = $("#emplDoB1").val();
        let emplPhone = $("#emplPhone1").val();
        let emplEmail = $("#emplEmail1").val();
        $.get({
            url: `/addTrainee?emplId=${emplId}&&emplAccount=${emplAccount}&&emplName=${emplName}&&emplDoB=${emplDoB}&&emplPhone=${emplPhone}&&emplEmail=${emplEmail}`,
            success: function (res) {
                $("#modal-content").html(res);
                $("#emplId1").val(emplId);
                $("#emplAccount1").val(emplAccount);
                $("#emplName1").val(emplName);
                $("#emplDoB1").val(emplDoB);
                $("#emplPhone1").val(emplPhone);
                $("#emplEmail1").val(emplEmail);
            },
            error: function (request, status, error) {
                $("#modal-content").html(request.responseText);
                console.log("pagingError")
            },
        });
    })

    $(document).on("change", "#sizePageTrainee2", function () {
        let classCode = $("#classCode").val();
        let emplId = $("#emplId").val();
        let emplAccount = $("#emplAccount").val();
        let emplName = $("#emplName").val();
        let emplDoB = $("#emplDoB").val();
        let emplPhone = $("#emplPhone").val();
        let emplEmail = $("#emplEmail").val();
        let pageSize = $("#sizePageTrainee2").val();
        $.get({
            url: `/trainee/Search/${classCode}?emplId=${emplId}&&emplAccount=${emplAccount}&&emplName=${emplName}&&emplDoB=${emplDoB}&&emplPhone=${emplPhone}&&emplEmail=${emplEmail}&&sizePage=${pageSize}`,
            success: function (res) {
                $("#view").html(res);
                $("#traineeImportFile").trigger("click");
                appendDataSearch(emplId, emplAccount, emplName, emplDoB, emplPhone, emplEmail);
            },
            error: function (request, status, error) {
                $("#view").html(request.responseText);
                $("#traineeImportFile").trigger("click");
                console.log("pagingError")
            },
        });
    })

    	$(document).on("click", ".traineeClick", function() {
		let idElement = $(this).attr("id");
		let numberIndex = parseInt(idElement);
		console.log(idElement);
		$.get({
			url: `/viewTrainee/${numberIndex}`,
			success: function(res) {
				$("#view").html(res);
			},
			error: function() {
				console.log("error");
			},
		});
	})

    $(document).on("click", "#addTrainee", function () {
        $.get({
            url: `/addTrainee?currentPage=&&sizePage=&&emplId=&&emplAccount=&&emplName=&&emplDoB=&&emplPhone=&&emplEmail=`,
            success: function (res) {
                $("#modal-content").html(res);
            },
            error: function () {
                console.log("error addTrainee")
            }
        })
    })

    $(document).on("change", "#sizePageAddTrainee1", function () {
        let page = $("#current-page1").val();
        $.get({
            url: `/addTrainee?currentPage=${page}&&sizePage=5&&emplId=${emplId1}&&emplAccount=${emplAccount1}&&emplName=${emplName1}&&emplDoB=${emplDoB1}&&emplPhone=${emplPhone1}&&emplEmail=${emplEmail1}`,
            success: function (res) {
                $("#modal-content").html(res);
            },
            error: function (request, status, error) {
                $("#view").html(request.responseText);
                console.log("pagingError")
            },
        });
    })

    	$(document).on('click', "#searchButtonTrainee", function() {
		let emplIdSearch = $("#emplIdSearch").val();
		let accountSearch = $("#accountSearch").val();
		let nameSearch = $("#nameSearch").val();
		let DOBSearch = $("#DOBSearch").val();
		let phoneSearch = $("#phoneSearch").val();
		let emailSearch = $("#emailSearch").val();
		let sizePage = $("#sizePageSelect").val();

		$.get({
			url: `/traineeListing/?traineeCandidateId=${emplIdSearch}&&sizePage=${sizePage}&&account=${accountSearch}&&fullName=${nameSearch}&&dateOfBirth=${DOBSearch}&&phone=${phoneSearch}&&email=${emailSearch}`,
			success: function(res) {
				$("#view").html(res);
			},
			error: function(res) {
				console.log(res)
			}
		})
	})
})

function collectStatus(currentSelect, emplId) {
    trainees.forEach(function (value, index) {
        if (value.id == emplId) {
            value.status = currentSelect.value
        }
    })
}


function appendDataSearch(emplId, emplAccount, emplName, emplDoB, emplPhone, emplEmail) {
    $("#emplId").val(emplId);
    $("#emplAccount").val(emplAccount);
    $("#emplName").val(emplName);
    $("#emplDoB").val(emplDoB);
    $("#emplPhone").val(emplPhone);
    $("#emplEmail").val(emplEmail);
}


