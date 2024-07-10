import * as cornerstone from '@cornerstonejs/core';
import * as cornerstoneDICOMImageLoader from '@cornerstonejs/dicom-image-loader';
import * as dicomParser from 'dicom-parser';

$(document).ready(function() {

    // 뷰 포트가 추가될 요소
    const content = document.getElementById('thumbnail');

    // Cornerstone 초기화
    const init = async () => {
        await cornerstone.init();

        cornerstoneDICOMImageLoader.external.cornerstone = cornerstone;
        cornerstoneDICOMImageLoader.external.dicomParser = dicomParser;

        var config = {
            maxWebWorkers: navigator.hardwareConcurrency || 1,
            startWebWorkersOnDemand: true,
            taskConfiguration: {
                decodeTask: {
                    initializeCodecsOnStartup: false,
                },
                sleepTask: {
                    sleepTime: 3000,
                },
            },
        };
        cornerstoneDICOMImageLoader.webWorkerManager.initialize(config);
    }

    init();

    // 시작날짜 끝날짜
    let startDate = $('#startDate').val();
    let endDate = $('#endDate').val();
    $("#startDate").on("change", function(event) {
        startDate = $('#startDate').val();
    });

    $("#endDate").on("change", function(event) {
        endDate = $('#endDate').val();
    });

    document.getElementById('endDate').value = new Date().toISOString().substring(0, 10);

    $('#search-form').submit(function(event) {
        event.preventDefault();
        fetchData();
    });

    function fetchData() {
        // 폼 데이터를 가져옴
        let formData = $('#search-form').serialize();

        $.ajax({
            url: '/search/detail',
            type: 'GET',
            data: formData, // 폼 데이터를 전송
            success: function(data) {
                let tbody = $('.results-section tbody');
                let countArea = $('#search-count');
                tbody.empty();
                countArea.empty();
                let count = '<p>' + data.length + '명의 환자를 찾았습니다.</p>';
                countArea.append(count);
                data.forEach(function(item) {
                    if(item.reportstatus === 3){
                        item.reportstatus = "읽지않음";
                    }else if(item.reportstatus === 5){
                        item.reportstatus = "예비판독";
                    }else if(item.reportstatus === 6){
                        item.reportstatus = "판독";
                    }
                    var row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
                        // 담당 환자 추가를 위한 체크박스
                        '<td class="td-patientBookmark-checkbox-container">' + `<input type="checkbox" class="checkbox-patientBookmark" id=${item.pid} />` + '</td>' +
                        '<td>' + item.pid + '</td>' +
                        '<td>' + item.pname + '</td>' +
                        '<td>' + item.modality + '</td>' +
                        '<td>' + item.studydesc + '</td>' +
                        '<td>' + item.studydate + '</td>' +
                        '<td>' + item.reportstatus + '</td>' +
                        '<td>' + item.seriescnt + '</td>' +
                        '<td>' + item.imagecnt + '</td>' +
                        '</tr>';
                    tbody.append(row);
                });
            },
            error: function(error) {
                console.error('Error fetching data', error);
            }
        });
    }

    // 클릭시 studyKey 얻기
    $('.results-section tbody').on('click', '.tr-area', function(e) {
        let id = $(this).attr('id');
        content.innerHTML = '<p>썸네일</p>';
        $.ajax({
            url: '/search/file',
            type: 'GET',
            data: { studyKey: id },
            success: function(data) {
                console.log("파일을 처리 중입니다.");

                // 여러 이미지를 처리하기 위해 imageIds 배열 생성
                data.forEach((fileData, index) => {
                    let byteCharacters = atob(fileData.base64Content);
                    let byteNumbers = new Array(byteCharacters.length);
                    for (let i = 0; i < byteCharacters.length; i++) {
                        byteNumbers[i] = byteCharacters.charCodeAt(i);
                    }
                    let byteArray = new Uint8Array(byteNumbers);
                    let blob = new Blob([byteArray], { type: fileData.fileType });
                    const url = URL.createObjectURL(blob);
                    const imageId = `dicomweb:${url}`;

                    render(imageId, index); // 이미지를 렌더링하기 위해 imageId와 index를 render 함수에 전달
                });
            },
            error: function(error) {
                console.error('파일을 가져오는 중 오류 발생', error);
            }
        });

        // render 함수 수정: imageId와 index를 받아 새로운 element에 렌더링
        const render = (imageId, index) => {
            // 새로운 div 요소 생성
            const element = document.createElement('div');
            element.style.width = '150px';
            element.style.height = '150px';
            element.style.marginBottom = '10px';
            element.id = `dicomImage${index}`;

            content.appendChild(element); // content 요소에 새로운 div 추가

            const renderingEngineId = `myRenderingEngine${index}`;
            const viewportId = `CT_AXIAL_STACK${index}`;
            const renderingEngine = new cornerstone.RenderingEngine(renderingEngineId);

            const viewportInput = {
                viewportId,
                element,
                type: cornerstone.Enums.ViewportType.STACK,
            };

            renderingEngine.enableElement(viewportInput);

            const viewport = renderingEngine.getViewport(viewportInput.viewportId);

            viewport.setStack([imageId], 0); // 스택 뷰포트에 이미지 설정
            viewport.render();
        }
    });
    $('.results-section tbody').on('dblclick', '.tr-area', function(e) {
        let id = $(this).attr('id');
        window.location.href=`viewer/${id}`;
    });
});
