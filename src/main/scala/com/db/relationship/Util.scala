package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */
case class AnySearchParameters(a: SearchParameter*) extends SearchParameter {
  override def filter(ar: Any): Boolean = a.foldLeft(false)((acc, item) => acc || item.filter(ar))
}
case class AllSearchParameters(a: SearchParameter*) extends SearchParameter {
  override def filter(ar: Any): Boolean = a.foldLeft(true)((acc, item) => acc && item.filter(ar))
}
case class NotSearchParameter(a: SearchParameter) extends SearchParameter {
  override def filter(ar: Any): Boolean = a.filter(ar)
}

trait SearchParameter {
  def filter(ar: Any): Boolean
  def &&(u: SearchParameter): SearchParameter = AllSearchParameters(this, u)
  def ||(u: SearchParameter): SearchParameter = AnySearchParameters(this, u)
}
