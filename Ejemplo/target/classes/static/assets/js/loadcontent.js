var nextPage = 0;

function loadMoreThemes() {
    var urlPage = "/table?page=" + nextPage;
    $.ajax({
        url: urlPage
    }).done(function (data) {
        $("#moreTheme").append(data);
        nextPage++;
    })
}