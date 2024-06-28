window.onload = () => {
    const studyContainerEle = document.getElementById("studyContainer");
}

function deleteCheckedStudiesToPatientBookmark() {
    const checkedCheckboxes = $(".study-checkbox:checked");
    const data = { pids: getIdsOfCheckedCheckboxes(checkedCheckboxes) };

    $.ajax({
        url: "/patientBookmark/delete",
        type: "DELETE",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(data) {
            window.location.href = "/patientBookmark/all";
        },
        error: function(error) {
            console.log(error);
        }
    });
}

function addCheckedStudiesToPatientBookmark() {
    const checkboxChecked = $(".study-checkbox:checkbox:checked");
    const data = { pids: getIdsOfCheckedCheckboxes(checkedCheckboxes) };

    $.ajax({
        url: "/patientBookmark/add",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(data) {
            window.location.href = "/patientBookmark/all";
        },
        error: function(error) {
            console.log(error);
        }
    });
}

function getIdsOfCheckedCheckboxes(checkboxes) {
    const checkedCheckboxIds = [];

    checkboxes.each(function() {
        checkedCheckboxIds.push(this.id);
    });

    return checkedCheckboxIds;
}