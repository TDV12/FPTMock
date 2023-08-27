var totalPage = $("#total-page").val();
var currentPage = $("#current-page").val();
var emplIdSearch2 = $("#emplId2").val();
var accountSearch = $("#account2").val();
var nameSearch = $("#name2").val();
var DOBSearch = $("#DOB2").val();
var phoneSearch = $("#phone2").val();
var emailSearch = $("#email2").val();
var sizePage2 = $("#size-page").val();
////var search = $("#dataSearch").val();

console.log(currentPage)
$("#pagination").twbsPagination({
    totalPages: totalPage == 0 ? 1 : totalPage,
    visible: 10,
    startPage: parseInt(currentPage),

    onPageClick: function (event, page) {


        if (currentPage != page) {
            $("#current-page").val(page);
            $.get({
                url: "./candidateListing?currentPage=" + page +"&&sizePage=" + sizePage2 + "&&emplId=" + emplIdSearch2 + "&&account=" + accountSearch + "&&fullName=" + nameSearch + "&&dateOfBirth=" + DOBSearch + "&&phone=" + phoneSearch + "&&email=" + emailSearch,
                success: function (res) {
                    $("#view").html(res);
                    console.log("abc")
                },
                error: function () {
                    console.log("NG ViewContent")
                }
            })
        }
    }
})
