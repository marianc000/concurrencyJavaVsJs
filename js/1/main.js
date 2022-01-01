import { fibonacci, measure } from '../shared/shared.js';
import os from 'os';


await measure(fibonacci);
console.log("CPUs",os.cpus().length);