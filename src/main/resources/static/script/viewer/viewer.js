import * as cornerstone from '@cornerstonejs/core';
import * as cornerstoneDICOMImageLoader from '@cornerstonejs/dicom-image-loader';
import * as dicomParser from 'dicom-parser';

import {setTools} from "./tools";

export let viewportIds = [];
export let renderingEngineIds = [];

// 뷰 포트 생성
const content = $(".content-image");

// 툴 정의


loadData();

async function loadData() {
    viewportIds = [];
    renderingEngineIds = [];
    let num = 0;
    const studyKey = $("#studyKey").val();
   let seriesKeys = await fetchSeriesKeys(studyKey);
   for (let seriesKey of seriesKeys) {

       let images = await fetchImages(seriesKey, studyKey);

       const imageIds = [];
       images.forEach((base64) => {
           const binary = atob(base64);
           const arraybuffer = Uint8Array.from(binary, c => c.charCodeAt(0));
           const imageId = `dicomweb:${URL.createObjectURL(new Blob([arraybuffer], {type: 'application/dicom'}))}`;
           imageIds.push(imageId);
       })
       render(imageIds, num);
       num ++;

   }

}


 const render = (imageIds, index) => {
    const element = document.createElement('dicomImage');
    element.style.width = '100%';
    element.style.height = '100%';
    element.classList.add("dicomImage");


    content.append(element);

    const renderingEngineId = `myRenderingEngine${index}`;
    const viewportId = `CT_AXIAL_STACK${index}`;


    try {
        setTools(viewportId, renderingEngineId);
    }catch (exception) {
        console.log(exception)
    }

    element.id = `${renderingEngineId}_${viewportId}`;

    renderingEngineIds.push(renderingEngineId);
    viewportIds.push(viewportId);
    const renderingEngine = new cornerstone.RenderingEngine(renderingEngineId);

    const viewportInput ={
        viewportId,
        element,
        type: cornerstone.Enums.ViewportType.STACK,
    }
    renderingEngine.enableElement(viewportInput);

    const viewport = renderingEngine.getViewport(viewportInput.viewportId);

    viewport.setStack(imageIds, 0);
    viewport.render();
}



async function fetchSeriesKeys(studyKey) {
    return await fetch(`/viewer/get/${studyKey}`, {
                method: "GET",
            })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    return data;
                })
                .catch(err => {
                    window.location.href = "/auth/sign-in";
                });



}

async function fetchImages(seriesKey, studyKey) {

    return await fetch(`/viewer/get/${studyKey}/${seriesKey}`, {
        method: "GET",
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            return data;
        })
        .catch(err => {
            window.location.href = "/auth/sign-in";
        });



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
};

init();