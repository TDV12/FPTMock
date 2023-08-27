var totalPage = $("#total-page").val();
var currentPage = $("#current-page").val();
var sizePage = $("#sizePage").val();
var totalPage1 = $("#total-page1").val();
var currentPage1 = $("#current-page1").val();
var totalPage2 = $("#total-page2").val();
var currentPage2 = $("#current-page2").val();
var locationId = $("#locationIdListing").val();
var className = $("#classNameId").val();
var status = $("#status").val();
var fromDate = $("#fromDate").val();
var toDate = $("#toDate").val();
var emplId = $("#emplId").val();
var emplAccount = $("#emplAccount").val();
var emplName = $("#emplName").val();
var emplDoB = $("#emplDoB").val();
var emplPhone = $("#emplPhone").val();
var emplEmail = $("#emplEmail").val();
var emplId1 = $("#emplId1").val();
var emplAccount1 = $("#emplAccount1").val();
var emplName1 = $("#emplName1").val();
var emplDoB1 = $("#emplDoB1").val();
var emplPhone1 = $("#emplPhone1").val();
var emplEmail1 = $("#emplEmail1").val();


$("#paginationClassListing").twbsPagination({
    totalPages: totalPage == 0 ? 1 : totalPage,
    visible: 10,
    startPage: parseInt(currentPage),
    onPageClick: function (event, page) {
        if (currentPage != page) {
            $("#current-page").val(page);
            $.get({
                url: "/classListing/Search?currentPage=" + page + "&&location=" + locationId + "&&className=" + className + "&&status=" + status + "&&fromDate=" + fromDate + "&&toDate=" + toDate + "&&sizePage=" + sizePage,
                success: function (res) {
                    $("#classListingBody").html(res)
                },
                error: function () {
                    console.log("error");
                },
            });
        }
        ;
    }
})

$("#paginationClassInViewMode").twbsPagination({
    initiateStartPageClick: false,
    totalPages: (totalPage2 == 0) ? 1 : totalPage2,
    visible: 10,
    startPage: parseInt(currentPage2),
    onPageClick: function (event, page) {
        if (currentPage2 != page) {
            $("#current-page").val(page);
            let classCode = $("#classCode").val();
            let sizePage = $("#sizePageTrainee2").val();
            $.get({
                url: `/trainee/Search/${classCode}?currentPage=${page}&&sizePage=${sizePage}&&emplId=${emplId}&&emplAccount=${emplAccount}&&emplName=${emplName}&&emplDoB=${emplDoB}&&emplPhone=${emplPhone}&&emplEmail=${emplEmail}`,
                success: function (res) {
                    $("#view").html(res);
                    $("#traineeImportFile").trigger("click");
                },
                error: function (request, status, error) {
                    $("#view").html(request.responseText);
                    $("#traineeImportFile").trigger("click");
                    console.log("pagingError")
                },
            });
        }
        ;
    }
})

$("#paginationAddTrainee").twbsPagination({
    totalPages: totalPage1 == 0 ? 1 : totalPage1,
    visible: 10,
    startPage: parseInt(currentPage1),
    onPageClick: function (event, page) {
        if (currentPage1 != page) {
            $("#current-page1").val(page);
            let sizePage = $("#sizePageAddTrainee1").val();
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
        }
        ;
    }
})




