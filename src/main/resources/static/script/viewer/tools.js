import * as cornerstoneTools from "@cornerstonejs/tools";

const {
    //1. 툴선언
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
    StackScrollMouseWheelTool,
    AngleTool,
    ArrowAnnotateTool,
    ProbeTool,
    LengthTool,
} = cornerstoneTools;

const { MouseBindings } = csToolsEnums;

const toolGroupId = 'NAVIGATION_TOOL_GROUP_ID';
const toolGroup = ToolGroupManager.createToolGroup(toolGroupId);

//2. 엘리먼트 불러오기
const moveBtn = document.getElementById('defaultTool');
const windowBtn = document.getElementById('windowLevel');
// const invertBtn = document.getElementById('invert');
const angleBtn = document.getElementById('activateAngle');
const arrowBtn = document.getElementById('activateArrowAnnotate');
const probeBtn = document.getElementById('activateProbe');
// let annotationBox = document.getElementById('annotationBox');
//3. 버튼체크
let isPanToolActive = false;
let isWindowActive = false;
// let isInvertActive;
let isAngleToolActive = false;
let isArrowToolActive = false;
let isProbeToolActive = false;
// let annotationDisplay = false;


// let selectedDivById = "";

//4. 버튼별 함수 지정
moveBtn.addEventListener('click', function() {
    movement_pan();
})
windowBtn.addEventListener('click', function() {
    windowLevel();
})
// annotationBox.addEventListener('click', function () {
//     showAnnotationBox();
// })
// invertBtn.addEventListener('click', function () {
//     if (selectedDivById.getAttribute('invert') === 'unchecked') {
//         selectedDivById.setAttribute('invert', 'checked');
//         isInvertActive = true;
//     } else {
//         selectedDivById.setAttribute('invert', 'unchecked');
//         isInvertActive = false;
//     }
//     invertImageWithWWWC(selectedDivById);
// });

angleBtn.addEventListener('click', function() {
    activateAngle();
})

arrowBtn.addEventListener('click', function() {
    activateArrow();
})

probeBtn.addEventListener('click', function() {
    activateProbe();
})

// function showAnnotationBox() {
//     if(!annotationDisplay) {
//         annotationBox.style.display='none'
//     }else {
//         annotationBox.style.display = 'inline-block';
//     }
//     annotationDisplay = !annotationDisplay;
// }
// window.addEventListener('click', function (e) {
//     if(e.target.id !== 'annotation')
//         annotationBox.style.display = 'none';
//     else {
//         showAnnotationBox();
//     }
// })






function toolMaker() {
    cornerstoneTools.init();
    //5. 툴add
    cornerstoneTools.addTool(PanTool);
    cornerstoneTools.addTool(WindowLevelTool);
    cornerstoneTools.addTool(StackScrollMouseWheelTool);
    cornerstoneTools.addTool(AngleTool);
    cornerstoneTools.addTool(MagnifyTool);
    cornerstoneTools.addTool(ArrowAnnotateTool);
    cornerstoneTools.addTool(ProbeTool);
    //6. 툴그룹에 add
    toolGroup.addTool(PanTool.toolName, {cursor:'move'});
    toolGroup.addTool(WindowLevelTool.toolName, {cursor:'window'});
    toolGroup.addTool(StackScrollMouseWheelTool.toolName, {cursor:'scroll'})
    toolGroup.addTool(AngleTool.toolName, {cursor:'angle'});
    toolGroup.addTool(MagnifyTool.toolName, {cursor:'mag'});
    toolGroup.addTool(ArrowAnnotateTool.toolName, {cursor:'arrow'});
    toolGroup.addTool(ProbeTool.toolName, {cursor:'probe'});
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

//7. 함수 만들기

function movement_pan() {
    // const PanTool = cornerstoneTools.PanTool;
    if(isPanToolActive) {
        toolGroup.setToolDisabled('Pan');
        toolGroup.setToolDisabled('StackScrollMouseWheel');
        toolGroup.setToolDisabled('Magnify')
        // cornerstoneTools.setToolDisabled('Pan');
    }else {
        // cornerstoneTools.addTool(PanTool);
        toolGroup.setToolActive('Pan', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
        toolGroup.setToolActive('StackScrollMouseWheel', {bindings:[{mouseButton:csToolsEnums.MouseBindings.Auxiliary}]})
        toolGroup.setToolActive('Magnify', {bindings:[{mouseButton:csToolsEnums.MouseBindings.Secondary}]})
        // toolGroup.setToolActive('StackScroll', {bindings:[{mouseButton:csToolsEnums.MouseBindings.Secondary}]})
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

// function invertImageWithWWWC(divById) {
//     const selectedDiv = cornerstone.getEnabledElement(divById).element;
//     const viewport = cornerstone.getViewport(selectedDiv);
//     viewport.invert = invertCheck;
//     cornerstone.setViewport(selectedDiv, viewport);
// }
//
// function invertHandler(divById) {
//     selectedDivById = divById;
//     const invertVal = divById.getAttribute('invert');
//     if (invertVal === null)
//         divById.setAttribute('invert', 'unchecked'); // checked
// }

function activateAngle() {
    if(isAngleToolActive) {
        toolGroup.setToolDisabled('Angle');
    }else {
        toolGroup.setToolActive('Angle', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isAngleToolActive = !isAngleToolActive;
}

function activateArrow() {
    if (isArrowToolActive) {
        toolGroup.setToolDisabled('ArrowAnnotate');
    }else {
        toolGroup.setToolActive('ArrowAnnotate', {bindings:[{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isArrowToolActive = !isArrowToolActive;
}

function activateProbe() {
    if (isProbeToolActive) {
        toolGroup.setToolDisabled('Probe');
    }else {
        toolGroup.setToolActive('Probe', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isProbeToolActive = !isProbeToolActive;
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

