let studyKey = $("#studyKey").val();
const nextStudyKey = Number(studyKey) + 1;

document.getElementById("next").addEventListener("click", function() {
    window.location.href=`${nextStudyKey}`;
})
