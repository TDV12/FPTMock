$(document).ready(function () {


    $(document).on("change", "#exceptedStartDate", function () {
        checkStartDate("#exceptedStartDate");
    })

    $(document).on("change", "#actualStartDate", function () {
        checkStartDate("#actualStartDate");
    })

    $(document).on("change", "#exceptedEndDate", function () {
        checkEndDate("#exceptedStartDate", "#exceptedEndDate");
    })

    $(document).on("change", "#actualEndDate", function () {
        checkEndDate("#actualStartDate", "#actualEndDate");
    })

    $(document).on("click", "#generalButton", function () {
        $("#submitCreateClass").prop("disabled", false);
    })
    
       $(document).on("change", "#dateOfBirthValid", function () {
        checkBirthday("#dateOfBirthValid");
        console.log(9);
    })
    
         $(document).on("change", "#DOBSearch", function () {
        checkBirthday("#DOBSearch");
        console.log(9);
    })
    
         $(document).on("change", "#applicationDateValid", function () {
        checkApplicationDate("#applicationDateValid");
        console.log(9);
    })
          $(document).on("change", "#gradurationYearValid", function () {
        checkGradurationYear("#gradurationYearValid");
        console.log(9);
    })
        


})


const validateData = function (idForm) {
    $(`${idForm}`).validate({
        rules: {
            exceptedStartDate: "required",
            exceptedEndDate: "required",
            estimatedBudget: "required",
            learningPath: "required",
        },
        messages: {
            exceptedStartDate: "Enter the Excepted Start Date",
            exceptedEndDate: "Enter the Excepted End Date",
            estimatedBudget: "Enter the Estimated Budget ",
            learningPath: "Load the Learning Path File"
        },
        errorPlacement: function (error, element) {
            var placement = $(element).data('error');
            if (placement) {
                $(placement).append(error)
            } else {
                error.insertAfter(element);
            }
        }
    })
}

// check start date , if start date before current date -> false
function checkStartDate(id) {
    let pickDate = $(`${id}`).val();
    if (isDateBeforeToDay(pickDate)) {
        $(`${id}` + "Mess").text("Start Date must after Current Date");
        return false;
    } else {
        $(`${id}` + "Mess").text("");
        return true;
    }
}

function checkBirthday(id) {
    let bir = $(`${id}`).val();
    if (isDateBeforeToDay(bir)) {
        $(`${id}` + "Mess").text("");
        return false;
    } else {
        $(`${id}` + "Mess").text("The time must be before Current Date");
        return true;
    }
}
function checkApplicationDate(id) {
    let bir = $(`${id}`).val();
    if (isDateBeforeToDay(bir)) {
        $(`${id}` + "Mess").text("");
        return false;
    } else {
        $(`${id}` + "Mess").text("Application Date must be before Current Date");
        return true;
    }
}
function checkGradurationYear(id) {
    let bir = $(`${id}`).val();
    if (isDateBeforeToDay(bir)) {
        $(`${id}` + "Mess").text("");
        return false;
    } else {
        $(`${id}` + "Mess").text("Graduration Year must be before Current Date");
        return true;
    }
}

// check start date and end date, if end date > start date -> false
function checkEndDate(idElementStartDate, idElementEndDate) {
    let startDate = $(`${idElementStartDate}`).val();
    let endDate = $(`${idElementEndDate}`).val();
    if (!isDateBeforeDate(startDate, endDate)) {
        $(`${idElementEndDate}` + "Mess").text("End Date must after Start Date");
        return false;
    } else if (!(startDate) && !(endDate)) {
        return true;
    } else {
        $(`${idElementEndDate}` + "Mess").text("");
        return true;
    }
}

function isDateBeforeToDay(date) {
    return new Date(date) < new Date(new Date().toDateString());
}

function isDateBeforeDate(startDate, endDate) {
    return new Date(startDate) < new Date(endDate);
}

//check empty date
function checkDateEmpty(idStartDate, idEndDate) {
    let startDate = $(`${idStartDate}`).val();
    let endDate = $(`${idEndDate}`).val();
    if (!(startDate) && !(endDate)) {
        return false;
    }
    return true;
}

//common check date form
function checkInputDate(idExceptStart, idExceptedEnd, idActualStart, idActualEnd) {
    let isValidExceptedStartDate = checkStartDate(`${idExceptStart}`);
    let isValidExceptedEndDate = checkEndDate(`${idExceptStart}`, `${idExceptedEnd}`);
    const conditions = [isValidExceptedStartDate, isValidExceptedEndDate]
    if (checkDateEmpty(`${idActualStart}`, `${idActualEnd}`)) {
        let isValidActualStartDate = checkStartDate(`${idActualStart}`);
        let isValidActualEndDate = checkEndDate(`${idActualStart}`, `${idActualEnd}`);
        conditions.push(isValidActualStartDate);
        conditions.push(isValidActualEndDate);
    }
    conditions.forEach(function (currentValue) {
        if (currentValue === false) {
            return false;
        }
    })
    return true;
}

