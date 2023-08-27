$(document).ready(function () {
    //call api search location when location change
    $(document).on("change", "#locationId", function () {
        callApiGetLocationDetail();
    })

    //call api generated clazz code
    $(document).on("change", "#locationId,#exceptedStartDate,#className", function () {
        let locationId = $("#locationId").val();
        let exceptedStartDate = $("#exceptedStartDate").val();
        if (exceptedStartDate === "") {
            exceptedStartDate = null;
        }
        let clazzNameId = $("#className").val();
        $.get({
            url: `api/clazzCodeGenerated?locationId=${locationId}&&exceptedStartDate=${exceptedStartDate}&&clazzNameId=${clazzNameId}`,
            success: function (res) {
                $("#classCode").val(res);
            },
            error: function () {
                console.log("Error in generated clazz code")
            }
        })
    })


})

const callApiGetLocationDetail = function () {
    $("#detailLocation").html("");
    let value = $("#locationId").val();
    $.get({
        url: `/api/location/${value}`,
        success: function (res) {
            let optionValue = res;
            $.each(optionValue, function (key, val) {
                $("#detailLocation").append(
                    `<option  value="${val.detailLocationId}">${val.locationDetail}</option>`)
            })
        },
        error: function () {
            console.log("error")
        }
    })
}
