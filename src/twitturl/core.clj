(ns twitturl.core
  (:use [ring.middleware.params])
  (:use [hiccup.core])
  (:require [com.twinql.clojure.http :as http]))

(def url "http://search.twitter.com/search.json")

(defn search [query] (http/get url :query {:q query} :as :json))
(defn aslink [url] (html [:a {:href url} url]))

(def  urls (partial re-seq #"http://\S+"))

(defn linkify [text]
  (reduce #(.replace %1 %2 (aslink %2)) text (urls text)))

(defn format-tweet [tweet]
  [:li (:from_user tweet) 
   [:blockquote (-> tweet :text linkify)]])

(defn format-tweets [json]
  [:ul (->> json :content :results
            (remove #(-> % :text urls nil?))
            (map format-tweet))])

(defn handler [{{q "q"} :params}]
  { :content-type "text/html"
   :body (html [:body  (-> (search q) format-tweets)])})

(def app (-> handler wrap-params))

