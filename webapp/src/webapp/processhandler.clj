(ns webapp.processhandler)


(def PROCESS-BIN "python -u /Users/nicola/repos/graph_web_external_proc/extprocess.py")

(defn ext-process-output-stream [bin]
  "lazy sequence of strings, one per line"
  (->> (.exec (Runtime/getRuntime) bin)
    .getInputStream
    clojure.java.io/reader
    line-seq))
