package com.springapp.mvc.controllers;

import com.springapp.mvc.model_for_users.*;
import com.springapp.mvc.models2.*;
import com.springapp.mvc.service.MyServiceClass;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/")
public class UpdateController {
	@Autowired
	MyServiceClass myServiceClass;

	@RequestMapping(value = "/update_company",method = RequestMethod.POST)
	public String save_company(
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("login") String login,
			@RequestParam("password") String password,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("imgName") String imgName,
			@RequestParam("file") MultipartFile photo,HttpServletRequest httpServletRequest,ModelMap modelMap) {
		DealerAdmin dealerAdmin = (DealerAdmin) httpServletRequest.getSession().getAttribute("admin");
		try {
			dealerAdmin.getId();
			StringBuilder stringBuilder =null;
			if (photo != null && photo.getOriginalFilename() != "") {
				stringBuilder = new StringBuilder();
				String file_name = null;
				MultipartFile file = photo;
				file_name = photo.getOriginalFilename();
				file_name = FilenameUtils.getExtension(file_name);
				try {
					byte[] bytes = file.getBytes();
					String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+id+File.separator;
					File dir = new File(rootPath + File.separator);
					if (!dir.exists())
						dir.mkdirs();
					RandomString randomString = new RandomString(Integer.valueOf(id));
					String randomStr = randomString.nextString();
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + randomStr + "." + file_name);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stringBuilder.append(randomStr + "." + file_name);
					stream.close();
					FileInputStream fis = new FileInputStream(serverFile);
					BufferedImage bufferedImage = ImageIO.read(fis);
					BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
					File file1 = new File(rootPath+File.separator+"small"+File.separator);
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
					ImageIO.write(resBufferedImage,file_name,file2);
				} catch (Exception e) {}


			} else if (photo == null || photo.getOriginalFilename() == "") {}

			try {
				if (name != null && name.length() != 0 && !name.equals("")) {
					if (login != null && login.length() != 0 && !login.equals("")) {
						if (password != null && password.length() != 0 && !password.equals("")) {
							if (address != null && address.length() != 0 && !address.equals("")) {
								if (login != null && login.length() != 0 && !login.equals("")) {
									if (phone != null && phone.length() != 0 && !phone.equals(""))  {
										if (password != null && password.length() != 0 && !password.equals("")) {
											Company company = new Company();
											company.setId(Integer.valueOf(id));
											company.setName(name);
											company.setLogin(login);
											company.setAddress(address);
											company.setPassword(password);
											company.setPhone(phone);
											if(stringBuilder!=null){
												company.setPhoto(stringBuilder.toString());
											}else{
												company.setPhoto(imgName);
											}

											myServiceClass.updateCompany(company);
											modelMap.addAttribute("remove", false);
											modelMap.addAttribute("login", "");
											return "admin_add_company_result";
										}
									}
								}
							}
						}

					}
				}
			} catch (NullPointerException e) {}
		}catch (NullPointerException e){}
		return "sign_in_admin";
	}
	@RequestMapping(value = "/update_courier",method = RequestMethod.POST)
	public String update_courier(
			@RequestParam("id_comp") String id_comp,
			@RequestParam("courier_id") String courier_id,
			@RequestParam("courier_photo") String courier_photo,
			@RequestParam("name") String name,
			@RequestParam("l_name") String l_name,
			@RequestParam("address") String address,
			@RequestParam("login") String login,
			@RequestParam("phone") String phone,
			@RequestParam("password") String password,
			@RequestParam("file") MultipartFile photo,ModelMap modelMap,HttpServletRequest httpServletRequest) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
			StringBuilder stringBuilder = null;
			if (photo != null && photo.getOriginalFilename() != "") {
				stringBuilder = new StringBuilder();
				String file_name = null;
				MultipartFile file = photo;
				file_name = photo.getOriginalFilename();
				file_name = FilenameUtils.getExtension(file_name);
				try {
					byte[] bytes = file.getBytes();
					String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+id_comp+File.separator+"couriers"+File.separator;
					File dir = new File(rootPath + File.separator);
					if (!dir.exists())
						dir.mkdirs();
					RandomString randomString = new RandomString(Integer.valueOf(courier_id));
					String randomStr = randomString.nextString();
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + randomStr + "." + file_name);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stringBuilder.append(randomStr + "." + file_name);
					stream.close();
					FileInputStream fis = new FileInputStream(serverFile);
					BufferedImage bufferedImage = ImageIO.read(fis);
					BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
					File file1 = new File(rootPath+File.separator+"small"+File.separator);
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
					ImageIO.write(resBufferedImage,file_name,file2);
				} catch (Exception e) {
					return "<h1><font size=\"35px\" style=\"color:#006064\";>Oshibka zagruzki failov</font></h1>";
				}


			} else if (photo == null || photo.getOriginalFilename() == "") {}
			try {
				if (id_comp != null && id_comp.length() != 0 && !id_comp.equals("")) {
					if (name != null && name.length() != 0 && !name.equals("")) {
						if (l_name != null && l_name.length() != 0 && !l_name.equals("")) {
							if (address != null && address.length() != 0 && !address.equals("")) {
								if (login != null && login.length() != 0 && !login.equals("")) {
									if (phone != null && phone.length() != 0 && !phone.equals("")) {
										if (password != null && password.length() != 0 && !password.equals("")) {
											Courier courier = new Courier();
											Company company = new Company();
											company.setId(Integer.valueOf(id_comp));
											courier.setId(Integer.valueOf(courier_id));
											courier.setCompany(company);
											courier.setName(name);
											courier.setL_name(l_name);
											courier.setAddress(address);
											courier.setLogin(login);
											courier.setPhone(phone);
											courier.setPassword(password);
											if(stringBuilder!=null){
												courier.setPhoto(stringBuilder.toString());
											}else{
												courier.setPhoto(courier_photo);
											}

											myServiceClass.updateCourier(courier);
											modelMap.addAttribute("login", "");
											modelMap.addAttribute("success", "success");
											return "add_courier2";
										}
									}
								}
							}
						}

					}
				}
			} catch (NullPointerException e) {}
		}catch (NullPointerException e){}
		return "sign_in";
	}
	@RequestMapping(value = "/update_product",method = RequestMethod.POST)
	public String update_product(
			@RequestParam("id_comp") String id_comp,
			@RequestParam("product_category") String product_category,
			@RequestParam("product_id") String product_id,
			@RequestParam("product_photo") String product_photo,
			@RequestParam("product_name") String product_name,
			@RequestParam("product_price") String product_price,
			@RequestParam("product_price_recommendation") String product_price_recommendation,
			@RequestParam("product_weight") String product_weight,
			@RequestParam("product_description") String product_description,
			@RequestParam(value = "product_isReturned", required = false) String product_isReturned,
			@RequestParam(value = "product_isDebt", required = false) String product_isDebt,
			@RequestParam(value = "file",required = false) MultipartFile photo,ModelMap modelMap,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company2 company2 = (Company2)httpSession.getAttribute("company");

		try {
			company2.getId();
			StringBuilder stringBuilder = null;
			if (photo != null && photo.getOriginalFilename() != "") {
				stringBuilder = new StringBuilder();
				String file_name = null;
				MultipartFile file = photo;
				file_name = photo.getOriginalFilename();
				file_name = FilenameUtils.getExtension(file_name);
				try {
					byte[] bytes = file.getBytes();
					String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+id_comp+File.separator+"products"+File.separator;
					File dir = new File(rootPath + File.separator);
					if (!dir.exists()){
						dir.mkdirs();
					}
					RandomString randomString = new RandomString(Integer.valueOf(product_id));
					String randomStr = randomString.nextString();
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + randomStr + "." + file_name);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stringBuilder.append(randomStr + "." + file_name);
					stream.close();
					FileInputStream fis = new FileInputStream(serverFile);
					BufferedImage bufferedImage = ImageIO.read(fis);
					BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
					File file1 = new File(rootPath+File.separator+"small"+File.separator);
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
					ImageIO.write(resBufferedImage,file_name,file2);
				}catch (Exception e){e.printStackTrace();}


			} else if (photo == null || photo.getOriginalFilename() == "") {}
			try {
				if (product_category != null && product_category.length() != 0 && !product_category.equals("")) {
					if (product_name != null && product_name.length() != 0 && !product_name.equals("")) {
						if (product_price != null && product_price.length() != 0 && !product_price.equals("")) {
							if (product_price_recommendation != null && product_price_recommendation.length() != 0 && !product_price_recommendation.equals("")) {
								if (product_weight != null && product_weight.length() != 0 && !product_weight.equals("")) {
									if (product_description != null && product_description.length() != 0 && !product_description.equals("")) {

										Products products = new Products();
										Company company = new Company();

										company.setId(company2.getId());

										products.setId(Long.valueOf(product_id));
										products.setStatus(false);
										products.setCompany(company);
										products.setName(product_name);
										products.setPrice(product_price);
										products.setRec_price(product_price_recommendation);
										products.setWeight(product_weight);
										products.setDescription(product_description);

										if(product_isReturned!=null){
											products.setReturned(true);
										}else{
											products.setReturned(false);
										}
										if(product_isDebt!=null){
											products.setConsignation(true);
										}else{
											products.setConsignation(false);
										}
										if(stringBuilder!=null){
											products.setPhoto(stringBuilder.toString());
										}else{
											products.setPhoto(product_photo);
										}

										myServiceClass.updateProduct(products,httpSession,product_category);
										modelMap.addAttribute("success", "success");
										modelMap.addAttribute("productDeleted", false);
										return "add_product2";

									}
								}
							}
						}

					}
				}
			} catch (NullPointerException e) {}
		}catch (NullPointerException e){

		}
		return "sign_in";
	}
	@ResponseBody
	@RequestMapping(value = "/update_shop",method = RequestMethod.POST)
	public Object update_shop(
			@RequestParam("shopId") String shopId,
			@RequestParam("groupsUsId") String groupsUsId,
			@RequestParam("shop_photo") String shop_photo,
			@RequestParam("name") String name,
			@RequestParam("login") String login,
			@RequestParam("password") String password,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude,
			@RequestParam(value = "file",required = false) MultipartFile photo) {
		Success success = new Success();
		try {
			StringBuilder stringBuilder = null;
			if (photo != null && photo.getOriginalFilename() != "") {
				stringBuilder = new StringBuilder();
				String file_name = null;
				MultipartFile file = photo;
				file_name = photo.getOriginalFilename();
				file_name = FilenameUtils.getExtension(file_name);
				try {
					byte[] bytes = file.getBytes();
					String rootPath = "C:"+File.separator+"path"+File.separator+"shops"+File.separator;
					File dir = new File(rootPath + File.separator);
					if (!dir.exists())
						dir.mkdirs();
					RandomString randomString = new RandomString(Integer.valueOf(shopId));
					String randomStr = randomString.nextString();
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + randomStr + "." + file_name);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stringBuilder.append(randomStr + "." + file_name);
					stream.close();
					FileInputStream fis = new FileInputStream(serverFile);
					BufferedImage bufferedImage = ImageIO.read(fis);
					BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
					File file1 = new File(rootPath+File.separator+"small"+File.separator);
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
					ImageIO.write(resBufferedImage,file_name,file2);
				}catch (Exception e){e.printStackTrace();}


			} else if (photo == null || photo.getOriginalFilename() == "") {}
			try {
				if (name != null && name.length() != 0 && !name.equals("")) {
					if (login != null && login.length() != 0 && !login.equals("")) {
						if (password != null && password.length() != 0 && !password.equals("")) {
							if (address != null && address.length() != 0 && !address.equals("")) {
								if (login != null && login.length() != 0 && !login.equals("")) {
									if (phone != null && phone.length() != 0 && !phone.equals("")) {
										if (latitude != null && latitude.length() != 0 && !latitude.equals("")) {
											if (longitude != null && longitude.length() != 0 && !longitude.equals("")) {
												if (groupsUsId != null && groupsUsId.length() != 0 && !groupsUsId.equals("")) {
													if (password != null && password.length() != 0 && !password.equals("")) {
														Shops shops = new Shops();
														shops.setId(Integer.valueOf(shopId));
														shops.setName(name);
														shops.setLogin(login);
														shops.setAddress(address);
														shops.setPassword(password);
														shops.setPhone(phone);
														shops.setLatitude(latitude);
														shops.setLongitude(longitude);
														GroupsUs groupsUs = new GroupsUs();
														groupsUs.setId(Integer.valueOf(groupsUsId));
														shops.setGroupsus(groupsUs);
														if(stringBuilder!=null){
															shops.setPhoto(stringBuilder.toString());
														}else{
															shops.setPhoto(shop_photo);
														}
														myServiceClass.updateShop(shops);
														success.setPhoto(stringBuilder.toString());
														success.setResult(200);
														return success;
													}
												}
											}
										}
									}
								}
							}
						}

					}
				}
			} catch (NullPointerException e) {}
		}catch (NullPointerException e){e.printStackTrace();}
		success.setResult(500);
		return success;
	}
	@ResponseBody
	@RequestMapping(value = "/update_shop_from_client",method = RequestMethod.POST)
	public Object update_shop_from_client(
			@RequestParam("shopId") String shopId,
			@RequestParam("groupsUsId") String groupsUsId,
			@RequestParam("shop_photo") String shop_photo,
			@RequestParam("name") String name,
			@RequestParam("login") String login,
			@RequestParam("password") String password,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude) {
		Success success = new Success();
		try {
				if (name != null && name.length() != 0 && !name.equals("")) {
					if (login != null && login.length() != 0 && !login.equals("")) {
						if (password != null && password.length() != 0 && !password.equals("")) {
							if (address != null && address.length() != 0 && !address.equals("")) {
								if (login != null && login.length() != 0 && !login.equals("")) {
									if (phone != null && phone.length() != 0 && !phone.equals("")) {
										if (latitude != null && latitude.length() != 0 && !latitude.equals("")) {
											if (longitude != null && longitude.length() != 0 && !longitude.equals("")) {
												if (groupsUsId != null && groupsUsId.length() != 0 && !groupsUsId.equals("")) {
													if (password != null && password.length() != 0 && !password.equals("")) {
														Shops shops = new Shops();
														shops.setId(Integer.valueOf(shopId));
														shops.setName(name);
														shops.setLogin(login);
														shops.setAddress(address);
														shops.setPassword(password);
														shops.setPhone(phone);
														shops.setLatitude(latitude);
														shops.setLongitude(longitude);
														GroupsUs groupsUs = new GroupsUs();
														groupsUs.setId(Integer.valueOf(groupsUsId));
														shops.setGroupsus(groupsUs);
														shops.setPhoto(shop_photo);
														myServiceClass.updateShop(shops);
														success.setResult(200);
														return success;
													}
												}
											}
										}
									}
								}
							}
						}

					}
				}
		}catch (NullPointerException e){e.printStackTrace();}
		success.setResult(500);
		return success;
	}

	@RequestMapping(value = "/update_category",method = RequestMethod.POST)
	public String update_category(@RequestParam("mainCategory") String mainCategory,
								@RequestParam("mainCategoryId") String mainCategoryId,
								HttpServletRequest httpServletRequest,ModelMap model) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
			Categories categories = new Categories();
			categories.setId(Integer.valueOf(mainCategoryId));
			categories.setCategory(mainCategory);
			Company company = new Company();
			company.setId(company2.getId());
			categories.setCompany(company);
			myServiceClass.updateCategory(categories);
			model.addAttribute("message", "add_category!");
			model.addAttribute("categoryDeleted",false);
			return "add_category2";
		}catch(NullPointerException e){
			return "sign_in";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/update_und_group",method = RequestMethod.POST)
	public Object update_und_group(@RequestBody UndGroups2 undGroups2) {
        Company company = new Company();
		company.setId(undGroups2.getCompId());
		Shops shops = new Shops();
		shops.setId(undGroups2.getShopId());
		Groups groups = new Groups();
		groups.setId(undGroups2.getGrId());
		UndGroups undGroups = new UndGroups();

		undGroups.setId(undGroups2.getId());
		undGroups.setShopDebt(undGroups2.getDebt());
		undGroups.setShopCons(undGroups2.isShopCons());
		undGroups.setShops(shops);
		undGroups.setGroups(groups);
		undGroups.setCompany(company);

		boolean result = myServiceClass.update_und_group(undGroups);
		if(result){
			Success success = new Success();
			success.setResult(200);
			return success;
		}else{
			Success success = new Success();
			success.setResult(500);
			return success;

		}
	}


	@RequestMapping(value = "/update_group",method = RequestMethod.POST)
	public String update_group(@RequestParam("priceGroup") String priceGroup,
							   @RequestParam("id") String id,
							   @RequestParam("compId") String compId,
							   @RequestParam("groupName") String groupName,

							   @RequestParam(value = "pn", required = false) String pn,
							   @RequestParam(value = "vt", required = false) String vt,
							   @RequestParam(value = "sr", required = false) String sr,
							   @RequestParam(value = "ch", required = false) String ch,
							   @RequestParam(value = "pt", required = false) String pt,
							   @RequestParam(value = "sb", required = false) String sb,
							   @RequestParam(value = "vs", required = false) String vs){


				Groups groups = new Groups();
				Company company = new Company();
				company.setId(Integer.valueOf(compId));
				groups.setId(Integer.valueOf(id));
				groups.setCompany(company);
				groups.setName(groupName);
				if(priceGroup!=null&&priceGroup.length()!=0&&!priceGroup.equals("")){
					groups.setPriceGroup(priceGroup);
				}else{
					groups.setPriceGroup(priceGroup);
				}
		        if(pn!=null&&pn.equals("on")){
					groups.setPn(true);
				}else{
					groups.setPn(false);
				}
				if(vt!=null&&vt.equals("on")){
					groups.setVt(true);
				}else{
					groups.setVt(false);
				}
				if(sr!=null&&sr.equals("on")){
					groups.setSr(true);
				}else{
					groups.setSr(false);
				}
				if(ch!=null&&ch.equals("on")){
					groups.setCh(true);
				}else{
					groups.setCh(false);
				}
				if(pt!=null&&pt.equals("on")){
					groups.setPt(true);
				}else{
					groups.setPt(false);
				}
				if(sb!=null&&sb.equals("on")){
					groups.setSb(true);
				}else{
					groups.setSb(false);
				}
				if(vs!=null&&vs.equals("on")){
					groups.setVs(true);
				}else{
					groups.setVs(false);
				}
        boolean result = myServiceClass.updateGroup(groups);
		if(result){
			return "groupsParamResult";
		}
		return "all_shops";

	}
	@ResponseBody
	@RequestMapping(value = "/update_courier_from_client",method = RequestMethod.POST)
	public Object update_courier_from_client(
			@RequestParam("id_comp") String id_comp,
			@RequestParam("courier_id") String courier_id,
			@RequestParam("courier_photo") String courier_photo,
			@RequestParam("name") String name,
			@RequestParam("l_name") String l_name,
			@RequestParam("address") String address,
			@RequestParam("login") String login,
			@RequestParam("phone") String phone,
			@RequestParam("password") String password) {
			try {
				if (id_comp != null && id_comp.length() != 0 && !id_comp.equals("")) {
					if (name != null && name.length() != 0 && !name.equals("")) {
						if (l_name != null && l_name.length() != 0 && !l_name.equals("")) {
							if (address != null && address.length() != 0 && !address.equals("")) {
								if (login != null && login.length() != 0 && !login.equals("")) {
									if (phone != null && phone.length() != 0 && !phone.equals("")) {
										if (password != null && password.length() != 0 && !password.equals("")) {
											Courier courier = new Courier();
											Company company = new Company();
											company.setId(Integer.valueOf(id_comp));
											courier.setId(Integer.valueOf(courier_id));
											courier.setCompany(company);
											courier.setName(name);
											courier.setL_name(l_name);
											courier.setAddress(address);
											courier.setLogin(login);
											courier.setPhone(phone);
											courier.setPassword(password);
											courier.setPhoto(courier_photo);

											myServiceClass.updateCourier(courier);
											return true;
										}
									}
								}
							}
						}

					}
				}
			} catch (NullPointerException e) {}
		return false;
	}
	BufferedImage createResizedCopy(Image originalImage,
									int scaledWidth, int scaledHeight,
									boolean preserveAlpha)
	{
		System.out.println("resizing...");
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}
}