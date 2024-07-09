function adjustTextareaHeight(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}

$(document).ready(function() {
    console.log(window);
    $('textarea[readonly]').each(function() {
        adjustTextareaHeight(this);
    });

    $("#close-button").click(e => {
        window.close();
    });

    window.opener.onunload = function() {window.close();}
});