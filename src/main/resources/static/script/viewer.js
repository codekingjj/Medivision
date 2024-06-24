import * as cornerstone from "@cornerstonejs/core";
import * as cornerstoneDICOMImageLoader from "@cornerstonejs/dicom-image-loader";
import * as dicomParser from "dicom-parser";

// Final code
const content = document.getElementById('content');
const element = document.createElement('div');
element.style.width = '500px';
element.style.height = '500px';

content.appendChild(element);

const input = document.getElementById("file");

input.addEventListener("change", () => {
    // 파일 변경 시
    // 파일 읽고 -> 버퍼(바이너리 데이터를 가져와 imageId 생성)
    // 이미지 랜더링을 위한 render 메소드 완성
    const files = e.target.files;

    const reader = new FileReader();

    reader.onload = (file) => {
        const data = file.target.result;

        render(data)
    }

    reader.readAsArrayBuffer(files[0])
});

const render = (arrayBuffer) => {
    const imageId = `dicom:${URL.createObjectURL(new Blob([arrayBuffer], { type: 'application/dicom'}))}`;

    console.log(imageId);

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
                initializeCodecsOneStartup: false,
            },
            sleepTask: {
                sleepTime: 3000,
            }
        }
    };

    cornerstoneDICOMImageLoader.webWorkerManager.initialize(config);

    render();
}

init();