import * as cornerstoneTools from "@cornerstonejs/tools";
const {
    //1. 툴선언
    //도구 -> 돋보기
    MagnifyTool,
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
    EraserTool,
    PlanarRotateTool,
} = cornerstoneTools;

const { MouseBindings } = csToolsEnums;

const toolGroupId = 'NAVIGATION_TOOL_GROUP_ID';
const toolGroup = ToolGroupManager.createToolGroup(toolGroupId);

//2. 엘리먼트 불러오기
const content = document.getElementsByClassName('content');
const moveBtn = document.getElementById('defaultTool');
const windowBtn = document.getElementById('windowLevel');
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
const zoomBtn = document.getElementById('activateZoom');
const rotateBtn = document.getElementById('activateRotate');
const rRotateBtn = document.getElementById('rRotate');
//3. 버튼체크
let isPanToolActive = false;
let isWindowActive = false;
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
let isZoomToolActive = false;
let isRotateToolActive = false;

//4. 버튼별 함수 지정
moveBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'Pan') {
        return;
    }else {
        toolDisabled(setActive);
    }
    movement_pan();
})
windowBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'WindowLevel') {
        return;
    }else {
        toolDisabled(setActive);
    }
    windowLevel();
})

angleBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'Angle') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateAngle();
})
arrowBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'ArrowAnnotate') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateArrow();
})
probeBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'Probe') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateProbe();
})
lengthBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'Length') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateLength();
})
rectangleBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'RectangleROI') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateRectangle();
})
ellipticalBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'EllipticalROI') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateElliptical();
})
freeHandBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'PlanarFreehandContourSegmentationTool') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateFreeHand();
})
bidirectionBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'Bidirectional') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateBidirection();
})
cobbangleBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'CobbAngle') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateCobbAngle();
})
eraserBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'Eraser') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateEraser();
})
zoomBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'Zoom') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateZoom()
})
rotateBtn.addEventListener('click', function() {
    const setActive = toolGroup.getActivePrimaryMouseButtonTool();
    if (setActive === 'PlanarRotate') {
        return;
    }else {
        toolDisabled(setActive);
    }
    activateRotate();
})

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
    cornerstoneTools.addTool(ZoomTool);
    cornerstoneTools.addTool(PlanarRotateTool);
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
    toolGroup.addTool(ZoomTool.toolName, {cursor:'zoom'});
    toolGroup.addTool(PlanarRotateTool.toolName, {cursor:'rotate'});
}

toolMaker();


document.getElementById("workList").addEventListener("click", function () {
    window.location.href = "/select"
})

//7. 함수 만들기

function toolDisabled(setActive) {
    switch (setActive) {
        case 'Pan' :
            movement_pan();
            break;
        case 'WindowLevel' :
            windowLevel();
            break;
        case 'Angle' :
            activateAngle();
            break;
        case 'ArrowAnnotate' :
            activateAngle();
            break;
        case 'Probe' :
            activateProbe();
            break;
        case 'Length' :
            activateLength();
            break;
        case 'RectangleROI':
            activateRectangle();
            break;
        case 'EllipticalROI':
            activateElliptical();
            break;
        case 'PlanarFreehandContourSegmentationTool':
            activateFreeHand();
            break;
        case 'Bidirectional':
            activateBidirection();
            break;
        case 'CobbAngle' :
            activateCobbAngle();
            break;
        case 'Eraser' :
            activateEraser();
            break;
        case 'Zoom':
            activateZoom();
            break;
        case 'PlanarRotate':
            activateRotate();
            break;
    }
}

function movement_pan() {
    if(isPanToolActive) {
        moveBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Pan');
        toolGroup.setToolDisabled('StackScrollMouseWheel');
        toolGroup.setToolDisabled('Magnify')
    }else {
        moveBtn.style.backgroundColor = '#9b9b9b';
        toolGroup.setToolActive('Pan', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
        toolGroup.setToolActive('StackScrollMouseWheel', {bindings:[{mouseButton:csToolsEnums.MouseBindings.Auxiliary}]})
        toolGroup.setToolActive('Magnify', {bindings:[{mouseButton:csToolsEnums.MouseBindings.Secondary}]})
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
function activateZoom() {
    if(isZoomToolActive) {
        zoomBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('Zoom');
    }else {
        zoomBtn.style.backgroundColor='#9b9b9b';
        toolGroup.setToolActive('Zoom', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isZoomToolActive = !isZoomToolActive;
}

function activateRotate() {
    if (isRotateToolActive) {
        rotateBtn.style.backgroundColor = 'white';
        toolGroup.setToolDisabled('PlanarRotate');
    }else {
        rotateBtn.style.backgroundColor='#9b9b9b';
        toolGroup.setToolActive('PlanarRotate', {bindings: [{mouseButton: csToolsEnums.MouseBindings.Primary}]});
    }
    isRotateToolActive = !isRotateToolActive;
}

export const setTools = (viewportId, renderingEngineId) => {
    toolGroup.addViewport(viewportId, renderingEngineId);
}
movement_pan();

document.addEventListener('contextmenu', function(event) {
    event.preventDefault();
});



