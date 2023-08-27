$(document).ready(function () {
    getView("/dashboard")
    $(document).on("click", "#createNewClass", function () {
        getView("/createNewClass")
    })

    $(document).on("click", "#classListing", function () {
        getView("/classListing")
    })

    $(document).on("click", "#dashboard", function () {
        getView("/dashboard")
    })


    $(document).on("click", "#traineeListing", function () {
        getView("/traineeListing")
    })
    

    $(document).on("click", "#traineeViewListing", function () {
        getView("/traineeListing")
    })

    $(document).on("click", "#ClassInformation", function () {
        $.get({
            url: "/classInformation",
            success: function (res) {
                $("#subview").html(res);
                callLocationAPI();
            },
            error: function (res) {
                console.log(res);
            },
        });
    })

    $(document).on("click", "#closeCreateCandidate", function () {
        getView("/candidateListing")
    })

    $(document).on("click", "#closeUpdateCandidate", function () {
        getView("/candidateListing")
    })

    $(document).on("click", "#candidateListing", function () {
        getView("/candidateListing")
    })

    $(document).on("click", "#createNewCandidate", function () {
        getView("/createNewCandidate")
    })

    $(document).on("click", "#CandidateInformation", function () {
        getSubView("/candidateInformation")
    })

    $(document).on("click", "#resultCandidate", function () {
        getSubView("/resultCandidate")
    })
    
     $(document).on("click", "#importTrainee", function () {
        getView("/importTrainee")
    })

})

const getView = function (value) {
    $.get({
        url: `${value}`,
        success: function (res) {
            $("#view").html(res);
        },
        error: function (request, status, error) {
            $("#view").html(request.responseText);
        },
    });
};

const getSubView = function (value) {
    $.get({
        url: `${value}`,
        success: function (res) {
            $("#subview").html(res);
        },
        error: function (res) {
            console.log(res);
        },
    });
};
const callLocationAPI = function () {
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