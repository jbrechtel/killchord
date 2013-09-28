(ns kill-chord.templates
  (:require [net.cgrand.enlive-html :as html]))

(html/deftemplate index-template "templates/index.html"
  [title]
  [:body :div :div :h3] (html/content title))
