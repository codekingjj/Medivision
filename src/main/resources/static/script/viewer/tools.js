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
    RectangleROITool,
    EllipticalROITool,
    PlanarFreehandContourSegmentationTool,
    BidirectionalTool,
    CobbAngleTool,
    EraserTool
} = cornerstoneTools;

const { MouseBindings } = csToolsEnums;

const toolGroupId = 'NAVIGATION_TOOL_GROUP_ID';
const toolGroup = ToolGroupManager.createToolGroup(toolGroupId);

//2. 엘리먼트 불러오기
const content = document.getElementsByClassName('content');
const moveBtn = document.getElementById('defaultTool');
const windowBtn = document.getElementById('windowLevel');
// const invertBtn = document.getElementById('invert');
const angleBtn = document.getElementById('activateAngle');
const arrowBtn = document.getElementById('activateArrowAnnotate');
const probeBtn = document.getElementById('activateProbe');
const lengthBtn = document.getElementById('activateLength');
const rectangleBtn = document.getElementById('activateRectangleRIO');
const ellipticalBtn = document.getElementById('activateEllipticalROI');
const freeHandBtn = document.getElementById('activateFreeHand');
const bidirectionBtn = document.getElementById('activateBidirectional');
const cobbangleBtn = document.getElementById('activateCobbAngle');
const eraserBtn = document.getElementById('activateEraser');
// let annotationBox = document.getElementById('annotationBox');
//3. 버튼체크
let isPanToolActive = false;
let isWindowActive = false;
// let isInvertActive;
let isAngleToolActive = false;
let isArrowToolActive = false;
let isProbeToolActive = false;
let isLengthTollActive = false;
let isRectangleTollActive = false;
let isEllipticalToolActive = false;
let isFreeHandToolActive = false;
let isBidirectionToolActive = false;
let isCobbAngleToolActive = false;
let isEraserToolActive =false;
// let annotationDisplay = false;


// let selectedDivById = "";


//4. 버튼별 함수 지정
moveBtn.addEventListener('click', function() {
    cornerstoneTools.init();
    movement_pan();
})
windowBtn.addEventListener('click', function() {
    cornerstoneTools.init();
    windowLevel();
})
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
lengthBtn.addEventListener('click', function() {
    activateLength();
})
rectangleBtn.addEventListener('click', function() {
    activateRectangle();
})
ellipticalBtn.addEventListener('click', function() {
    activateElliptical();
})
freeHandBtn.addEventListener('click', function() {
    activateFreeHand();
})
bidirectionBtn.addEventListener('click', function() {
    activateBidirection();
})
cobbangleBtn.addEventListener('click', function() {
    activateCobbAngle();
})
eraserBtn.addEventListener('click', function() {
    activateEraser();
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
    cornerstoneTools.addTool(LengthTool);
    cornerstoneTools.addTool(RectangleROITool);
    cornerstoneTools.addTool(EllipticalROITool);
    cornerstoneTools.addTool(PlanarFreehandContourSegmentationTool);
    cornerstoneTools.addTool(BidirectionalTool);
    cornerstoneTools.addTool(CobbAngleTool);
    cornerstoneTools.addTool(EraserTool);
    //6. 툴그룹에 add
    toolGroup.addTool(PanTool.toolName, {cursor:'move'});
    toolGroup.addTool(WindowLevelTool.toolName, {cursor:'window'});
    toolGroup.addTool(StackScrollMouseWheelTool.toolName, {cursor:'scroll'})
    toolGroup.addTool(AngleTool.toolName, {cursor:'angle'});
    toolGroup.addTool(MagnifyTool.toolName, {cursor:'mag'});
    toolGroup.addTool(ArrowAnnotateTool.toolName, {cursor:'arrow'});
    toolGroup.addTool(ProbeTool.toolName, {cursor:'probe'});
    toolGroup.addTool(LengthTool.toolName, {cursor:'length'});
    toolGroup.addTool(RectangleROITool.toolName, {cursor:'rectangle'});
    toolGroup.addTool(EllipticalROITool.toolName, {cursor:'elliptical'})
    toolGroup.addTool(PlanarFreehandContourSegmentationTool.toolName, {cursor:'freeHand'});
    toolGroup.addTool(BidirectionalTool.toolName, {cursor:'bidirectional'});
    toolGroup.addTool(CobbAngleTool.toolName, {cursor:'cobbAngle'});
    toolGroup.addTool(EraserTool.toolName, {cursor:'eraser'});
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
        moveBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Pan');
        toolGroup.setToolDisabled('StackScrollMouseWheel');
        toolGroup.setToolDisabled('Magnify')
        // cornerstoneTools.setToolDisabled('Pan');
    }else {
        moveBtn.style.backgroundColor = '#9b9b9b';
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
        windowBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('WindowLevel')
    }else {
        windowBtn.style.backgroundColor = '#9b9b9b';
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
        angleBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Angle');
    }else {
        angleBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('Angle', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isAngleToolActive = !isAngleToolActive;
}

function activateArrow() {
    if (isArrowToolActive) {
        arrowBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('ArrowAnnotate');
    }else {
        arrowBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('ArrowAnnotate', {bindings:[{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isArrowToolActive = !isArrowToolActive;
}

function activateProbe() {
    if (isProbeToolActive) {
        probeBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Probe');
    }else {
        probeBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('Probe', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isProbeToolActive = !isProbeToolActive;
}

function activateLength() {
    if (isLengthTollActive) {
        lengthBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Length');
    }else {
        lengthBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('Length', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isLengthTollActive = !isLengthTollActive;
}

function activateRectangle() {
    if (isRectangleTollActive) {
        rectangleBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('RectangleROI');
    }else {
        rectangleBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('RectangleROI', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isRectangleTollActive = !isRectangleTollActive;
}
function activateElliptical() {
    if (isEllipticalToolActive) {
        ellipticalBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('EllipticalROI');
    }else {
        ellipticalBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('EllipticalROI', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isEllipticalToolActive = !isEllipticalToolActive;
}
function activateFreeHand() {
    if (isFreeHandToolActive) {
        freeHandBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('PlanarFreehandContourSegmentationTool');
    }else {
        freeHandBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('PlanarFreehandContourSegmentationTool', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isFreeHandToolActive = !isFreeHandToolActive;
}
function activateBidirection() {
    if (isBidirectionToolActive) {
        bidirectionBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Bidirectional');
    }else {
        bidirectionBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('Bidirectional', {bindings:[{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isBidirectionToolActive = !isBidirectionToolActive;
}
function activateCobbAngle() {
    if (isCobbAngleToolActive) {
        cobbangleBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('CobbAngle');
    }else {
        cobbangleBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('CobbAngle', {bindings: [{mouseButton:csToolsEnums.MouseBindings.Primary}]});
    }
    isCobbAngleToolActive = !isCobbAngleToolActive;
}
function activateEraser() {
    if (isEraserToolActive) {
        eraserBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Eraser');
    }else {
        eraserBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('Eraser', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isEraserToolActive = !isEraserToolActive;
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
movement_pan();

// function handleContextMenu(event) {
//     if (event.target === content) {
//         event.preventDefault();
//     }
// }
document.addEventListener('contextmenu', function(event) {
    event.preventDefault();
});

