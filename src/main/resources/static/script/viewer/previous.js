const studyKey = $("#studyKey").val();
const previousStudyKey = studyKey - 1;

document.getElementById("previous").addEventListener("click", function() {
    window.location.href=`${previousStudyKey}`;
})
