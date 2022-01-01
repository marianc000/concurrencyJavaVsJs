// main.js
import { Worker, isMainThread, threadId } from 'worker_threads';
import constant from './constant.js';

console.log("in thread", threadId, "constant value is", constant);

if (isMainThread) { 
    for (let i = 0; i < 3; i++)
        new Worker(new URL(import.meta.url));
}  