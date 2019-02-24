var nextPage = 1;

function loadMoreThemes() {
    var urlPage = "/table?page=" + nextPage;
    
    $.ajax({
        url: urlPage
    }).done(function (data) {
        $("#moreTheme").append(data);
        nextPage++;
        
        if (indexTheme == nextPage){
        	$("#buttonMoreThemes").hide();
        }
    })
}