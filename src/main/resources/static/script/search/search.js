$(document).ready(function() {

    let count = "";

    let startDate = $('#startDate').val();
    let endDate = $('#endDate').val();
    $("#startDate").on("change", function(event) {
        startDate = $('#startDate').val();
        console.log(startDate);
        console.log(typeof startDate);
    });


    $("#endDate").on("change", function(event) {
        endDate = $('#endDate').val();
        console.log(endDate);
        console.log(typeof endDate);
    });
    console.log(startDate);
    console.log(endDate);

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
                console.log(data);
                tbody.empty();
                countArea.empty();
                console.log(data);
                count = '<p>' + data.length + '명의 환자를 찾았습니다.</p>';
                countArea.append(count);
                data.forEach(function(item) {
                    if(item.reportstatus === 3){
                        item.reportstatus = "읽지않음";
                    }else if(item.reportstatus === 5){
                        item.reportstatus = "예비판독";
                    }else if(item.reportstatus === 6){
                        item.reportstatus = "판독";
                    }
                    var row = '<tr id=' +item.studyKey +' class="tr-area" >' +
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
        alert(id);
        getThumbnail(id);

    });

    function getThumbnail(studyKey){
        $.ajax({
            url: '/search/',
            type: 'GET',
            data: id,
            success: function (data){
                let thumbnail = $('.thumbnail');
                thumbnail.empty();
                data.forEach(function(item) {
                    let row = "테스테스테스트";
                    thumbnail.append(row);
                });
            },error: function(error) {
                console.error('Error fetching data', error);
            }

        })
    }


    import * as cornerstone from '@cornerstonejs/core'
    import * as cornerstoneDICOMImageLoader from '@cornerstonejs/dicom-image-loader'
    import * as dicomParser from 'dicom-parser'

    const input = document.getElementById("file");

// 뷰 포트 생성
    const content = document.getElementById('content');
    const element = document.createElement('div');
    element.style.width = '500px';
    element.style.height = '500px';

    content.appendChild(element);

// 파일 리딩
    input.addEventListener("change", e => {
        // 파일이 변화하면
        // 파일을 읽고 -> 버퍼 (바이너리 데이터를 가져와)
        // ImageId를 생성 (dicomweb://)
        // 이미지 렌더링을 위한 render 메소드 완성

        const files = e.target.files;

        const reader = new FileReader();
        reader.onload = (file) => {
            const data = file.target.result;
            render(data);
        }
        reader.readAsArrayBuffer(files[0]);
    })

    const render = (arrayBuffer) => {
        // Get Cornerstone imageIds and fetch metadata into RAM
        const imageId = `dicomweb:${URL.createObjectURL(new Blob([arrayBuffer], {type : 'application/dicom'}))}`;
        console.log('imageId : ', imageId);

        const imageIds = [imageId];

        const renderingEngineId = 'myRenderingEngine';
        const viewportId = 'CT_AXIAL_STACK';
        const renderingEngine = new cornerstone.RenderingEngine(renderingEngineId);

        const viewportInput = {
            viewportId,
            element,
            type: cornerstone.Enums.ViewportType.STACK,
        };

        renderingEngine.enableElement(viewportInput);

        const viewport = renderingEngine.getViewport(viewportInput.viewportId);

        viewport.setStack(imageIds, 0);

        viewport.render();
    }

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
});
