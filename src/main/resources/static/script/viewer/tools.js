// import * as cornerstone from '@cornerstonejs/core'
// import { WindowLevelTool, ZoomTool} from '@cornerstonejs/tools'
// // import cornerstone3DTools
// //     from "@cornerstonejs/tools/dist/cjs/utilities/segmentation/InterpolationManager/InterpolationManager";
// // import cornerstone3DTools
// //     from "@cornerstonejs/tools/src/utilities/segmentation/InterpolationManager/InterpolationManager";
import * as cornerstoneTools from "@cornerstonejs/tools";
// import dicomParser from "dicom-parser";
import{viewportIds, renderingEngineIds} from "./viewer.js";
import * as cornerstone from '@cornerstonejs/core';

const {
    //도구 -> 돋보기
    MagnifyTool,
    TrackballRotateTool,
    //도구 -> 확대, 축소
    ZoomTool,
    ToolGroupManager,
    Enums: csToolsEnums,
    //사진 이동
    PanTool,
    WindowLevelTool,
} = cornerstoneTools;

const { MouseBindings } = csToolsEnums;

const moveBtn = document.getElementById('defaultTool');
const windowBtn = document.getElementById('windowLevel');
const invertBtn = document.getElementById('invert');
let annotationBox = document.getElementById('annotationBox');

let isPanToolActive = false;
let isWindowActive = false;
let isInvertActive;
let annotationDisplay = false;

let selectedDivById = "";

moveBtn.addEventListener('click', function() {
    movement_pan();
})
windowBtn.addEventListener('click', function() {
    windowLevel();
})
annotationBox.addEventListener('click', function () {
    showAnnotationBox();
})
invertBtn.addEventListener('click', function () {
    if (selectedDivById.getAttribute('invert') === 'unchecked') {
        selectedDivById.setAttribute('invert', 'checked');
        isInvertActive = true;
    } else {
        selectedDivById.setAttribute('invert', 'unchecked');
        isInvertActive = false;
    }
    invertImageWithWWWC(selectedDivById);
});

function showAnnotationBox() {
    if(!annotationDisplay) {
        annotationBox.style.display='none'
    }else {
        annotationBox.style.display = 'inline-block';
    }
    annotationDisplay = !annotationDisplay;
}
// window.addEventListener('click', function (e) {
//     if(e.target.id !== 'annotation')
//         annotationBox.style.display = 'none';
//     else {
//         showAnnotationBox();
//     }
// })

function activateAngle() {

}



const toolGroupId = 'NAVIGATION_TOOL_GROUP_ID';
const toolGroup = ToolGroupManager.createToolGroup(toolGroupId);

function toolMaker() {
    cornerstoneTools.init();
    cornerstoneTools.addTool(PanTool);
    cornerstoneTools.addTool(WindowLevelTool);
    toolGroup.addTool(PanTool.toolName, {cursor:'move'});
    toolGroup.addTool(WindowLevelTool.toolName, {cursor:'window'});
    // toolGroup.addTool(Invert)
}

toolMaker();






// cornerstoneTools.addTool(PanTool);
// toolGroup.addTool(PanTool.toolName);


// $(".dicomImage").on("click", e => {
//     console.log("clicked")
//     const id = e.target;
//     console.log(id)
//     // console.log("engine id: " + id[0]);
//     // console.log("viewport id: " + id[1]);
//     //
//     toolGroup.addViewport(id[1], id[0]);
// })

// for (let i = 0; i < viewportIds.length; i++) {
//     const viewportId = viewportIds[i];
//     const renderingEngineId = renderingEngineIds[i];
//     toolGroup.addViewport(viewportId, renderingEngineId);
// }


document.getElementById("workList").addEventListener("click", function () {
    window.location.href = "/select"
})

function movement_pan() {
    // const PanTool = cornerstoneTools.PanTool;
    if(isPanToolActive) {
        toolGroup.setToolDisabled('Pan')
        // cornerstoneTools.setToolDisabled('Pan');
    }else {
        // cornerstoneTools.addTool(PanTool);
        toolGroup.setToolActive('Pan', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
        // cornerstoneTools.setToolActive('Pan', {mouseButtonMask: 1})
    }
    isPanToolActive = !isPanToolActive;
}

function windowLevel() {
    if(isWindowActive) {
        toolGroup.setToolDisabled('WindowLevel')
    }else {
        toolGroup.setToolActive('WindowLevel', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isWindowActive = !isWindowActive;
}

function invertImageWithWWWC(divById) {
    const selectedDiv = cornerstone.getEnabledElement(divById).element;
    const viewport = cornerstone.getViewport(selectedDiv);
    viewport.invert = invertCheck;
    cornerstone.setViewport(selectedDiv, viewport);
}

function invertHandler(divById) {
    selectedDivById = divById;
    const invertVal = divById.getAttribute('invert');
    if (invertVal === null)
        divById.setAttribute('invert', 'unchecked'); // checked
}


//
// const init = async () => {
//     await cornerstone.init();
//     cornerstoneDICOMImageLoader.external.cornerstone = cornerstone;
//     cornerstoneDICOMImageLoader.external.dicomParser = dicomParser;
//
//     var config = {
//         maxWebWorkers: navigator.hardwareConcurrency || 1,
//         startWebWorkersOnDemand: true,
//         taskConfiguration: {
//             decodeTask: {
//                 initializeCodecsOnStartup: false,
//             },
//             sleepTask: {
//                 sleepTime: 3000,
//             },
//         },
//     };
//
//     cornerstoneDICOMImageLoader.webWorkerManager.initialize(config);
// };
//
//
//
// // cornerstone3DTools.addTool(ZoomTool);
// // cornerstone3DTools.addTool(WindowLevelTool);
// //
// // const toolGroupId = 'ToolGroup';
// // const toolGroup = ToolGroupManager.createToolGroup(toolGroupId);
// //
// //
// // toolGroup.addTool(ZoomTool.toolName);
// // toolGroup.addTool(WindowLevelTool.toolName);
// //
// // toolGroup.setToolActive(WindowLevelTool.toolName, {
// //     bindings: [
// //         {
// //             mouseButton: csToolsEnums.MouseBindings.Primary, // Left Click
// //         },
// //     ],
// // });
// //
// // toolGroup.setToolActive(ZoomTool.toolName, {
// //     bindings: [
// //         {
// //             mouseButton: csToolsEnums.MouseBindings.Secondary, // Right Click
// //         },
// //     ],
// // });
//

export const setTools = (viewportId, renderingEngineId) => {
    // cornerstoneTools.init();
    //
    // cornerstoneTools.addTool(PanTool.toolName);
    //
    // toolGroup.addTool(PanTool.toolName, {cursor:'move'});

    toolGroup.addViewport(viewportId, renderingEngineId);
}

