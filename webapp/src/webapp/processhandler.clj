(ns webapp.processhandler
  ;; (:use [clojure.java.shell :only [sh]])
  (:require [clojure.core.async :as async]
            ;; [clojure.java.shell :as shell]
            )

  )

(def PROCESS-BIN "python -u /Users/nicola/repos/graph_web_external_proc/extprocess.py")


(defn ext-process-output-stream [bin]
  (->> (.exec (Runtime/getRuntime) bin)
    .getInputStream
    clojure.java.io/reader
    line-seq))


(defn test []
  (let [stream (ext-process-output-stream PROCESS-BIN)]
    (doall (map println stream))))
