// import * as cornerstone from '@cornerstonejs/core'
// import { WindowLevelTool, ZoomTool} from '@cornerstonejs/tools'
// // import cornerstone3DTools
// //     from "@cornerstonejs/tools/dist/cjs/utilities/segmentation/InterpolationManager/InterpolationManager";
// // import cornerstone3DTools
// //     from "@cornerstonejs/tools/src/utilities/segmentation/InterpolationManager/InterpolationManager";
import * as cornerstoneTools from "@cornerstonejs/tools";
// import dicomParser from "dicom-parser";
import{viewportIds, renderingEngineIds} from "./viewer.js";

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

const moveBtn = document.getElementById('move');


let isPanToolActive = false;
moveBtn.addEventListener('click', function() {
    console.log(viewportIds);
    console.log(renderingEngineIds);
    movement_pan();
})
const toolGroupId = 'NAVIGATION_TOOL_GROUP_ID';
const toolGroup = ToolGroupManager.createToolGroup(toolGroupId);

function toolMaker() {
    cornerstoneTools.init();
    cornerstoneTools.addTool(PanTool);
    toolGroup.addTool(PanTool.toolName, {cursor:'move'});
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


const { MouseBindings } = csToolsEnums;

document.getElementById("workList").addEventListener("click", function () {
    window.location.href = "/main"
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

